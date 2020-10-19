package com.coder_rangers.mobius_api.database.specifications

import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.models.Game
import org.springframework.data.jpa.domain.Specification

object GameSpecification {
    fun categoryTypeEqualTo(categoryType: Category.Type): Specification<Game> {
        return Specification { root, _, criteriaBuilder ->
            val categoryJoin = root.join<Game, Category>("category")
            criteriaBuilder.equal(categoryJoin.get<Category.Type>("type"), categoryType)
        }
    }
}
