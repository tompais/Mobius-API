package com.coder_rangers.mobius_api.dao.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IPatientDAO
import com.coder_rangers.mobius_api.database.repositories.IPatientRepository
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.User.Status.ACTIVE
import org.springframework.stereotype.Repository

@Repository
class PatientDAO(
    private val patientRepository: IPatientRepository
) : IPatientDAO {
    override fun findActivePatientByEmailAndPassword(email: String, password: String): Patient? =
        patientRepository.findByEmailAndPasswordAndStatus(email, password, ACTIVE)

    override fun findActivePatientById(id: Long): Patient? = patientRepository.findByIdAndStatus(id, ACTIVE)
    override fun saveOrUpdate(patient: Patient): Patient = patientRepository.saveAndFlush(patient)
}
