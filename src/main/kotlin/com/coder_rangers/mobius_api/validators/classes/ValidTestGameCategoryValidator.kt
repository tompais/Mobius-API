package com.coder_rangers.mobius_api.validators.classes

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.validators.annotations.ValidTestGameCategory
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidTestGameCategoryValidator : ConstraintValidator<ValidTestGameCategory, Game.Category> {
    override fun isValid(value: Game.Category, context: ConstraintValidatorContext) = value.isTestCategoryType
}
