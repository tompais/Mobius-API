package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.models.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface IGameRepository : JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
    @Query("SELECT MAX(g.id) FROM Game g WHERE g.category = ?1")
    @RestResource(exported = false)
    fun getMaxIdByCategory(category: Game.Category): Long

    @Query("SELECT MIN(g.id) FROM Game g WHERE g.category = ?1")
    @RestResource(exported = false)
    fun getMinIdByCategory(category: Game.Category): Long
}
