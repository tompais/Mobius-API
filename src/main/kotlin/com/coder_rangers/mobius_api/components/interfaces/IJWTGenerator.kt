package com.coder_rangers.mobius_api.components.interfaces

interface IJWTGenerator {
    fun generateJWT(email: String): String
}
