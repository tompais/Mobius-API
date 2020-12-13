package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.models.Guardian
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
@RepositoryRestResource(exported = false)
interface IGuardianRepository : JpaRepository<Guardian, Long> {
    fun getByEmail(email: String): Guardian?
}
