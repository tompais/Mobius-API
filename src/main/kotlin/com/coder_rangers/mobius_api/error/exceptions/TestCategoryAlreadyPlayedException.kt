package com.coder_rangers.mobius_api.error.exceptions

import com.coder_rangers.mobius_api.models.Game

class TestCategoryAlreadyPlayedException(id: Long, category: Game.Category) :
    ForbiddenException("The patient with id [$id] already played the category [$category]")
