package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.enums.TestStatus
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Patient.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
@RepositoryRestResource(exported = false)
interface IPatientRepository : JpaRepository<Patient, Long> {
    fun findByEmailAndPasswordAndStatus(email: String, password: String, status: Status): Patient?
    fun findByIdAndStatus(id: Long, status: Status): Patient?
    fun findByEmailAndStatus(email: String, status: Status): Patient?
    fun findAllByStatusAndTestStatus(status: Status, testStatus: TestStatus): Set<Patient>
}
