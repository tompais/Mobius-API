package com.coder_rangers.mobius_api.validators.classes

import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.validators.annotations.ValidTestCategoryType
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidTestCategoryTypeValidator : ConstraintValidator<ValidTestCategoryType, Category.Type> {
    override fun isValid(value: Category.Type, context: ConstraintValidatorContext) = value.isTestCategoryType
}
