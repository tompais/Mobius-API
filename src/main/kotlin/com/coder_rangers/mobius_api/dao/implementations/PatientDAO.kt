package com.coder_rangers.mobius_api.dao.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.database.repositories.IPatientRepository
import com.coder_rangers.mobius_api.enums.TestStatus.FINISHED
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Patient.Status.ACTIVE
import org.springframework.stereotype.Repository

@Repository
class PatientDAO(
    private val patientRepository: IPatientRepository
) : IPatientDAO {
    override fun getActivePatientByEmailAndPassword(email: String, password: String): Patient? =
        patientRepository.findByEmailAndPasswordAndStatus(email, password, ACTIVE)

    override fun findActivePatientById(id: Long): Patient? = patientRepository.findByIdAndStatus(id, ACTIVE)
    override fun createOrUpdatePatient(patient: Patient): Patient = patientRepository.saveAndFlush(patient)
    override fun getActivePatientByEmail(email: String): Patient? =
        patientRepository.findByEmailAndStatus(email, ACTIVE)

    override fun getActivePatientsWithFinishedTest(): Set<Patient> =
        patientRepository.findAllByStatusAndTestStatus(ACTIVE, FINISHED)
}
