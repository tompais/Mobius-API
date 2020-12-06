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
    @Query("SELECT MAX(g.id) FROM Game g WHERE g.category = ?1 AND g.isTestGame = ?2")
    @RestResource(exported = false)
    fun getMaxIdByCategory(category: Category, test: Boolean): Long

    @Query("SELECT MIN(g.id) FROM Game g WHERE g.category = ?1 AND g.isTestGame = ?2")
    @RestResource(exported = false)
    fun getMinIdByCategory(category: Category, test: Boolean): Long

    @Query("SELECT DISTINCT g.category FROM Game g WHERE g.isTestGame = false")
    @RestResource(exported = false)
    fun getNotTestCategories(): List<Category>
}
