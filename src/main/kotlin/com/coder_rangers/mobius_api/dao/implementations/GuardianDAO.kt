package com.coder_rangers.mobius_api.dao.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGuardianDAO
import com.coder_rangers.mobius_api.database.repositories.IGuardianRepository
import com.coder_rangers.mobius_api.models.Guardian
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class GuardianDAO @Autowired constructor(
    private val guardianRepository: IGuardianRepository
) : IGuardianDAO {
    override fun getGuardianByEmail(email: String): Guardian? = guardianRepository.getByEmail(email)
    override fun createGuardian(email: String): Guardian = guardianRepository.saveAndFlush(
        Guardian(
            id = 0,
            email = email
        )
    )
}
