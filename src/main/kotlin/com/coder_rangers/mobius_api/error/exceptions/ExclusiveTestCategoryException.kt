package com.coder_rangers.mobius_api.error.exceptions

import com.coder_rangers.mobius_api.models.Game.Category

class ExclusiveTestCategoryException(category: Category) :
    BadRequestException("The category [$category] is exclusive for the test.")
