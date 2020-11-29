package com.coder_rangers.mobius_api.validators.classes

import com.coder_rangers.mobius_api.validators.annotations.ValidPassword
import java.util.Base64
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidPasswordValidator : ConstraintValidator<ValidPassword, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext) =
        String(Base64.getDecoder().decode(value)).length in 6..16
}
