package com.coder_rangers.mobius_api.converters

import com.coder_rangers.mobius_api.models.Category
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CategoryTypeConverter : Converter<String, Category.Type> {
    override fun convert(source: String) = Category.Type.valueOf(source.toUpperCase())
}
