package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Guardian

interface IGuardianDAO {
    fun getGuardianByEmail(email: String): Guardian?
    fun createGuardian(email: String): Guardian
}
