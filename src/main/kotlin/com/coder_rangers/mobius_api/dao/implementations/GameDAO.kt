package com.coder_rangers.mobius_api.dao.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.database.repositories.IGameRepository
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class GameDAO @Autowired constructor(
    private val gameRepository: IGameRepository
) : IGameDAO {
    override fun getMaxIdByCategory(category: Category, isTestGame: Boolean): Long =
        gameRepository.getMaxIdByCategory(category, isTestGame)

    override fun getMinIdByCategory(category: Category, isTestGame: Boolean): Long =
        gameRepository.getMinIdByCategory(category, isTestGame)

    override fun getGameById(id: Long): Game? = gameRepository.findByIdOrNull(id)

    override fun getNotTestCategories(): List<Category> = gameRepository.getNotTestCategories()
}
