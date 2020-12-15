package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.coder_rangers.mobius_api.components.interfaces.IGameAnswersResolver
import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.error.exceptions.GameNotFoundException
import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.Game.Category.READING
import com.coder_rangers.mobius_api.services.implementations.GameService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GameServiceTest {
    @MockK
    private lateinit var gameDAO: IGameDAO

    @MockK
    @Suppress("UNUSED")
    private lateinit var textGameAnswersWithResultsResolver: IGameAnswersResolver<AnswerWithResult<String>>

    @MockK
    @Suppress("UNUSED")
    private lateinit var textGameAnswersResolver: IGameAnswersResolver<String>

    @MockK
    @Suppress("UNUSED")
    private lateinit var numericGameAnswersResolver: IGameAnswersResolver<Int>

    @MockK
    @Suppress("UNUSED")
    private lateinit var attentionGameAnswersResolver: IGameAnswersResolver<Char>

    @MockK
    @Suppress("UNUSED")
    private lateinit var comprehensionGameAnswersResolver: IGameAnswersResolver<String>

    @MockK
    @Suppress("UNUSED")
    private lateinit var drawingGameAnswersResolver: IGameAnswersResolver<String>

    @InjectMockKs
    private lateinit var gameService: GameService

    @Test
    fun getRandomGameByCategoryTypeThrowsNotFoundTest() {
        // GIVEN
        every { gameDAO.getIdsByCategory(any(), any()) } returns listOf(3, 5, 8)
        every { gameDAO.getGameById(any()) } returns null

        // THEN
        assertThat {
            gameService.getRandomGameByCategory(READING, false)
        }.isFailure().isInstanceOf(GameNotFoundException::class)
    }
}
