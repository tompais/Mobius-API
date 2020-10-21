package com.coder_rangers.mobius_api.validators.annotations

import com.coder_rangers.mobius_api.validators.classes.ValidTestGameCategoryValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ValidTestGameCategoryValidator::class])
@Target(VALUE_PARAMETER)
@Retention(RUNTIME)
annotation class ValidTestGameCategory(
    val message: String = "Invalid test category type.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
