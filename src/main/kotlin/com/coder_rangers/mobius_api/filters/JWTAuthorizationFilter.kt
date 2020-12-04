package com.coder_rangers.mobius_api.filters

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_FORBIDDEN

@Component
class JWTAuthorizationFilter(
    @Value("\${security.auth.secret_key}")
    private val authSecretKey: String
) : OncePerRequestFilter() {
    private companion object {
        private const val HEADER = "Authorization"
        private const val PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authorizationHeader = request.getHeader(HEADER)

            if (existsJWTToken(authorizationHeader)) {
                val claims: Claims = validateToken(authorizationHeader!!)

                if (claims["authorities"] != null) {
                    setUpSpringAuthentication(claims)
                } else {
                    SecurityContextHolder.clearContext()
                }
            } else {
                SecurityContextHolder.clearContext()
            }

            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            response.apply {
                status = SC_FORBIDDEN
            }.also {
                it.sendError(SC_FORBIDDEN, e.message)
            }
        }
    }

    private fun existsJWTToken(authorizationHeader: String?): Boolean =
        authorizationHeader != null && authorizationHeader.startsWith(PREFIX)

    private fun validateToken(authorizationHeader: String): Claims =
        authorizationHeader.removePrefix(PREFIX)
            .let { jwtToken ->
                Jwts.parser()
                    .setSigningKey(
                        authSecretKey.toByteArray()
                    )
                    .parseClaimsJws(jwtToken)
                    .body
            }

    /**
     * Method to authenticate us into Spring's flow.
     *
     * @param claims
     */
    private fun setUpSpringAuthentication(claims: Claims) {
        @Suppress("UNCHECKED_CAST")
        val authorities: List<String> = claims["authorities"] as List<String>

        UsernamePasswordAuthenticationToken(
            claims.subject,
            null,
            authorities.map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
        ).let { auth ->
            SecurityContextHolder.getContext().authentication = auth
        }
    }
}
