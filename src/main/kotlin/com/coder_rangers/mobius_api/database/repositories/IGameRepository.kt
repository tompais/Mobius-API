package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
@RepositoryRestResource(exported = false)
interface IGameRepository : JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
    @Query("SELECT g.id FROM Game g WHERE g.category = ?1 AND g.isTestGame = ?2")
    fun getIdsByCategory(category: Category, test: Boolean): List<Long>

    @Query("SELECT DISTINCT g.category FROM Game g WHERE g.isTestGame = false")
    fun getNotTestCategories(): List<Category>
}
