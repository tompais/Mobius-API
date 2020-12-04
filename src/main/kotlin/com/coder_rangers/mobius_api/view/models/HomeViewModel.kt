package com.coder_rangers.mobius_api.view.models

import com.coder_rangers.mobius_api.models.Game.Category

class HomeViewModel(
    val recommendedCategory: Category,

    val categories: List<Category>
)
