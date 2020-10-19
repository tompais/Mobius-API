package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.error.exceptions.PatientNotFoundException
import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.TestProgress
import com.coder_rangers.mobius_api.models.TestProgress.Status.IN_PROGRESS
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PatientService @Autowired constructor(
    private val patientDAO: IPatientDAO
) : IPatientService {
    private companion object {
        private val TEST_GAME_CATEGORIES = Category.Type.values().filter { it.isTestCategoryType }
    }

    override fun getMentalTestGames(id: Long) {
        val patient = getActivePatientById(id)

        val testProgress = patient.testProgress

        val nextGameCategory = getNextGameCategory(testProgress)


    }

    private fun getNextGameCategory(testProgress: TestProgress?): Category.Type? {
        return when {
            testProgress == null -> TEST_GAME_CATEGORIES.first()
            testProgress.status == IN_PROGRESS -> TEST_GAME_CATEGORIES.firstOrNull {
                it.ordinal == testProgress.lastCategoryPlayed.type.ordinal + 1
            }
            else -> null
        }
    }

    private fun getActivePatientById(id: Long): Patient =
        patientDAO.findActivePatientById(id) ?: throw PatientNotFoundException(id)
}
