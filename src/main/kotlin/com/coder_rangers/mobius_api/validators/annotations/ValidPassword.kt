package com.coder_rangers.mobius_api.validators.annotations

import com.coder_rangers.mobius_api.validators.classes.ValidPasswordValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ValidPasswordValidator::class])
@Target(FIELD)
@Retention(RUNTIME)
annotation class ValidPassword(
    val message: String = "The password must be between 6 and 16 characters.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
