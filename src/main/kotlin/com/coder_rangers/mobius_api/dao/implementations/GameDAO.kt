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
        gameRepository.getMaxTestIdByCategory(category)

    override fun getMinIdByCategory(category: Game.Category): Long =
        gameRepository.getMinTestIdByCategory(category)

    override fun getGameById(id: Long): Game? = gameRepository.findByIdOrNull(id)
}
