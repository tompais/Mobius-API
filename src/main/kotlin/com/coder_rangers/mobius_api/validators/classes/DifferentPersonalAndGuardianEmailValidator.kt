package com.coder_rangers.mobius_api.validators.classes

import com.coder_rangers.mobius_api.requests.SignUpRequest
import com.coder_rangers.mobius_api.validators.annotations.DifferentPersonalAndGuardianEmail
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class DifferentPersonalAndGuardianEmailValidator :
    ConstraintValidator<DifferentPersonalAndGuardianEmail, SignUpRequest> {
    override fun isValid(value: SignUpRequest, context: ConstraintValidatorContext): Boolean =
        value.personalEmail != value.guardianEmail
}
