package com.coder_rangers.mobius_api.validators.annotations

import com.coder_rangers.mobius_api.validators.classes.OverEighteenValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [OverEighteenValidator::class])
@Target(FIELD)
@Retention(RUNTIME)
annotation class OverEighteen(
    val message: String = "Should be 18 years or older",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
