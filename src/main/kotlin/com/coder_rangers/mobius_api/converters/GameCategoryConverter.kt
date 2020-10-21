package com.coder_rangers.mobius_api.converters

import com.coder_rangers.mobius_api.models.Game
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class GameCategoryConverter : Converter<String, Game.Category> {
    override fun convert(source: String) = Game.Category.valueOf(source.toUpperCase())
}
