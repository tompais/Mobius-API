package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Guardian

interface IGuardianService {
    fun getOrCreateGuardian(email: String): Guardian
}
