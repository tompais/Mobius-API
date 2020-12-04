package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.components.interfaces.IJWTGenerator
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.stereotype.Component

@Component
class JWTGenerator(
    @Value("\${security.auth.secret_key}")
    private val authSecretKey: String
) : IJWTGenerator {
    override fun generateJWT(email: String): String = AuthorityUtils
        .commaSeparatedStringToAuthorityList("ROLE_USER").let { grantedAuthorities ->
            Jwts
                .builder()
                .setId("MobiusMindJWT")
                .setSubject(email)
                .claim(
                    "authorities",
                    grantedAuthorities
                        .map { it.authority }
                )
                .signWith(
                    HS512,
                    authSecretKey.toByteArray()
                )
                .compact()
        }
}
