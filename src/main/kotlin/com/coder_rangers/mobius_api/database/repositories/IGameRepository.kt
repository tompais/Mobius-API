package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface IGameRepository : JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
    @Query("SELECT MAX(g.id) FROM Game g WHERE g.category = ?1 AND g.isTest = true")
    @RestResource(exported = false)
    fun getMaxTestIdByCategory(category: Category): Long

    @Query("SELECT MIN(g.id) FROM Game g WHERE g.category = ?1 AND g.isTest = true")
    @RestResource(exported = false)
    fun getMinTestIdByCategory(category: Category): Long

    fun getFirstByCategory(category: Category): Game
}
