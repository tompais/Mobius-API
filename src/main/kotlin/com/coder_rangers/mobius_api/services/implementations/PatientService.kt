package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Game.Category.LANGUAGE_AND_PRAXIS
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.TestProgress
import com.coder_rangers.mobius_api.models.TestProgress.Status.FINISHED
import com.coder_rangers.mobius_api.requests.categories.TestGameAnswersRequest
import com.coder_rangers.mobius_api.services.interfaces.IMentalTestService
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PatientService @Autowired constructor(
    private val patientDAO: IPatientDAO,
    private val mentalTestService: IMentalTestService
) : IPatientService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun getMentalTestGame(id: Long, nextGameCategory: Category): Game {
        val patient = getActivePatientById(id)

        return mentalTestService.getMentalTestGame(patient, nextGameCategory)
    }

    override fun processTestGameAnswers(id: Long, testGameAnswersRequest: TestGameAnswersRequest) {
        val patient = getActivePatientById(id)

        when (testGameAnswersRequest.category) {
            ORIENTATION -> logger.info("holi")
            else -> logger.info("byeee")
        }

        createOrUpdateTestProgress(patient, testGameAnswersRequest.category)
    }

    private fun getActivePatientById(id: Long): Patient =
        patientDAO.findActivePatientById(id) ?: throw PatientNotFoundException(id)

    private fun isLastTestCategory(category: Category) = category == LANGUAGE_AND_PRAXIS

    private fun createOrUpdateTestProgress(patient: Patient, category: Category) {
        if (patient.testProgress == null) {
            createTestProgress(patient, category)
        } else {
            updateTestProgress(patient, category)
        }

        patientDAO.saveOrUpdate(patient)
    }

    private fun createTestProgress(patient: Patient, category: Category) {
        patient.testProgress = TestProgress(
            id = 0,
            patient = patient,
            lastCategoryPlayed = category
        )
    }

    private fun updateTestProgress(patient: Patient, category: Category) {
        patient.testProgress?.apply {
            lastCategoryPlayed = category
            if (isLastTestCategory(category)) {
                status = FINISHED
            }
        }
    }
}
