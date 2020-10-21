package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface IPatientRepository : JpaRepository<Patient, Long> {
    fun findByEmailAndPasswordAndStatus(email: String, password: String, status: User.Status): Patient?
    fun findByIdAndStatus(id: Long, status: User.Status): Patient?
}
