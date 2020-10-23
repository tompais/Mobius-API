package com.coder_rangers.mobius_api.dao.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.database.repositories.IGameRepository
import com.coder_rangers.mobius_api.models.Game
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class GameDAO @Autowired constructor(
    private val gameRepository: IGameRepository
) : IGameDAO {
    override fun getMaxIdByCategory(category: Game.Category): Long =
        gameRepository.getMaxIdByCategory(category)

    override fun getMinIdByCategory(category: Game.Category): Long =
        gameRepository.getMinIdByCategory(category)

    override fun findGameById(id: Long): Game? = gameRepository.findByIdOrNull(id)

    override fun findGameByCategory(category: Game.Category): Game =
        gameRepository.getGameByCategory(category)
}
