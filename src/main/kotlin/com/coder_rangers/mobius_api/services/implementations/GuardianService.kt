package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGuardianDAO
import com.coder_rangers.mobius_api.models.Guardian
import com.coder_rangers.mobius_api.services.interfaces.IGuardianService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GuardianService @Autowired constructor(
    private val guardianDAO: IGuardianDAO
) : IGuardianService {
    override fun getOrCreateGuardian(email: String): Guardian =
        guardianDAO.getGuardianByEmail(email) ?: guardianDAO.createGuardian(email)
}
