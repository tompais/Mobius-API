package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.enums.TestStatus.FINISHED
import com.coder_rangers.mobius_api.enums.TestStatus.IN_PROGRESS
import com.coder_rangers.mobius_api.error.exceptions.DuplicatedPatientException
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.error.exceptions.TestNotFinishedException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Game.Category.DRAWING
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.requests.categories.GameAnswersRequest
import com.coder_rangers.mobius_api.responses.PatientTestResult
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import com.coder_rangers.mobius_api.view.models.HomeViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PatientService @Autowired constructor(
    private val patientDAO: IPatientDAO,
    private val mentalTestService: IMentalTestService,
    private val gameService: IGameService,
    private val taskResultService: ITaskResultService
) : IPatientService {
    override fun getGame(id: Long, gameCategory: Category, test: Boolean): Game {
        val patient = getActivePatientById(id)

        return if (test)
            mentalTestService.getMentalTestGame(patient, gameCategory, test)
        else
            gameService.getNotTestGame(patient, gameCategory, test)
    }

    override fun processGameAnswers(id: Long, gameAnswersRequest: GameAnswersRequest<*>) {
        val patient = getActivePatientById(id)

        if (gameAnswersRequest.areTestGameAnswers) {
            mentalTestService.processTestGameAnswers(patient, gameAnswersRequest)
            updateTestStatus(patient, gameAnswersRequest.category)
        } else {
            gameService.processGameAnswers(patient, gameAnswersRequest)
        }
    }

    override fun getTestResult(id: Long): PatientTestResult {
        val patient = getActivePatientById(id)

        if (patient.testStatus != FINISHED)
            throw TestNotFinishedException(id)

        return mentalTestService.getPatientTestResult(id)
    }

    override fun getHome(id: Long): HomeViewModel {
        getActivePatientById(id)

        val categories = gameService.getNotTestCategories()

        val recommendedCategory = taskResultService.getRecommendedCategory(id, categories)

        return HomeViewModel(recommendedCategory, categories.filterNot { it == recommendedCategory })
    }

    override fun getActivePatientByEmailAndPassword(email: String, password: String): Patient =
        patientDAO.getActivePatientByEmailAndPassword(email, password) ?: throw PatientNotFoundException()

    override fun createOrUpdatePatient(patient: Patient): Patient = try {
        patientDAO.createOrUpdatePatient(patient)
    } catch (dive: DataIntegrityViolationException) {
        throw DuplicatedPatientException(patient.email, dive)
    }

    override fun getActivePatientByEmail(email: String): Patient? = patientDAO.getActivePatientByEmail(email)

    override fun cleanTestProgress(id: Long) {
        getActivePatientById(id).also { patient ->
            patient.testStatus = IN_PROGRESS
            patient.taskResults?.forEach {
                it.patient = null
            }
            patient.taskResults?.clear()
            patientDAO.createOrUpdatePatient(patient)
        }
    }

    private fun getActivePatientById(id: Long): Patient =
        patientDAO.findActivePatientById(id) ?: throw PatientNotFoundException(id)

    private fun isLastTestCategory(category: Category) = category == DRAWING

    private fun updateTestStatus(patient: Patient, category: Category) {
        if (isLastTestCategory(category)) {
            patient.testStatus = FINISHED
            patientDAO.createOrUpdatePatient(patient)
        }
    }
}
