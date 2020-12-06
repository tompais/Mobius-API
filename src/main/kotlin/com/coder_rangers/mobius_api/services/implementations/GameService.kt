package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.components.interfaces.IGameAnswersResolver
import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.enums.TestStatus.FINISHED
import com.coder_rangers.mobius_api.error.exceptions.ExclusiveTestCategoryException
import com.coder_rangers.mobius_api.error.exceptions.GameNotFoundException
import com.coder_rangers.mobius_api.error.exceptions.TestNotFinishedException
import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.AttentionGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.NumericGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextGameAnswersRequest
import com.coder_rangers.mobius_api.requests.categories.TextGameAnswersWithResultsRequest
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GameService @Autowired constructor(
    private val gameDAO: IGameDAO,

    @Qualifier("textGameAnswersWithResultsResolver")
    private val textGameAnswersWithResultsResolver: IGameAnswersResolver<AnswerWithResult<String>>,

    @Qualifier("textGameAnswersResolver")
    private val textGameAnswersResolver: IGameAnswersResolver<String>,

    @Qualifier("numericGameAnswersResolver")
    private val numericGameAnswersResolver: IGameAnswersResolver<Int>,

    @Qualifier("attentionGameAnswersResolver")
    private val attentionGameAnswersResolver: IGameAnswersResolver<Char>,

    @Qualifier("comprehensionGameAnswersResolver")
    private val comprehensionGameAnswersResolver: IGameAnswersResolver<String>,

    @Qualifier("drawingGameAnswersResolver")
    private val drawingGameAnswersResolver: IGameAnswersResolver<String>
) : IGameService {
    override fun getRandomGameByCategory(category: Category, test: Boolean): Game {
        val minId = gameDAO.getMinIdByCategory(category, test)
        val maxId = gameDAO.getMaxIdByCategory(category, test)

        val randomId = (minId..maxId).random()

        return getGameById(randomId)
    }

    override fun getGameById(id: Long): Game = gameDAO.getGameById(id) ?: throw GameNotFoundException(id)

    override fun getNotTestCategories(): List<Category> = gameDAO.getNotTestCategories()

    override fun getNotTestGame(patient: Patient, gameCategory: Category, test: Boolean): Game {
        assertThatTestIsFinished(patient)

        assertThatIsNotTestCategory(gameCategory)

        return getRandomGameByCategory(gameCategory, test)
    }

    override fun processGameAnswers(patient: Patient, gameAnswersRequest: GameAnswersRequest<*>) {
        if (!gameAnswersRequest.areTestGameAnswers) {
            assertThatTestIsFinished(patient)
            assertThatIsNotTestCategory(gameAnswersRequest.category)
        }

        val game = getGameById(gameAnswersRequest.gameId)

        when (gameAnswersRequest.category) {
            Category.ORIENTATION, Category.MEMORY -> textGameAnswersWithResultsResolver.resolveAnswers(
                patient,
                game,
                (gameAnswersRequest as TextGameAnswersWithResultsRequest).patientTaskAnswersRequestList
            )
            Category.CALCULATION, Category.READING -> numericGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (gameAnswersRequest as NumericGameAnswersRequest).patientTaskAnswersRequestList
            )
            Category.ATTENTION -> attentionGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (gameAnswersRequest as AttentionGameAnswersRequest).patientTaskAnswersRequestList
            )
            Category.COMPREHENSION -> comprehensionGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (gameAnswersRequest as TextGameAnswersRequest).patientTaskAnswersRequestList
            )
            Category.DRAWING -> drawingGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (gameAnswersRequest as TextGameAnswersRequest).patientTaskAnswersRequestList
            )
            else -> textGameAnswersResolver.resolveAnswers(
                patient,
                game,
                (gameAnswersRequest as TextGameAnswersRequest).patientTaskAnswersRequestList
            )
        }
    }

    private fun assertThatTestIsFinished(patient: Patient) {
        if (patient.testStatus != FINISHED) {
            throw TestNotFinishedException(patient.id)
        }
    }

    private fun assertThatIsNotTestCategory(category: Category) {
        val notTestCategories = getNotTestCategories()

        if (category !in notTestCategories) {
            throw ExclusiveTestCategoryException(category)
        }
    }
}
