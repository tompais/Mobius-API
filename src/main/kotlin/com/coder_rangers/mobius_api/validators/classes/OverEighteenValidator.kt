package com.coder_rangers.mobius_api.validators.classes

import com.coder_rangers.mobius_api.validators.annotations.OverEighteen
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class OverEighteenValidator : ConstraintValidator<OverEighteen, LocalDate> {
    override fun isValid(value: LocalDate, context: ConstraintValidatorContext): Boolean = ChronoUnit.YEARS.between(
        value,
        LocalDate.now()
    ) >= 18
}
