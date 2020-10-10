package com.coder_rangers.mobius_api.validators.annotations

import com.coder_rangers.mobius_api.validators.classes.DifferentPersonalAndGuardianEmailValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [DifferentPersonalAndGuardianEmailValidator::class])
@Target(CLASS)
@Retention(RUNTIME)
annotation class DifferentPersonalAndGuardianEmail(
    val message: String = "Personal and guardian email must be different",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
