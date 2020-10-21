package com.coder_rangers.mobius_api.validators.annotations

import com.coder_rangers.mobius_api.validators.classes.ValidTestCategoryTypeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ValidTestCategoryTypeValidator::class])
@Target(VALUE_PARAMETER)
@Retention(RUNTIME)
annotation class ValidTestCategoryType(
    val message: String = "Invalid test category type.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
