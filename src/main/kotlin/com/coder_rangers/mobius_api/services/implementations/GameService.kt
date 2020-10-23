package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.error.exceptions.GameNotFoundException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GameService @Autowired constructor(
    private val gameDAO: IGameDAO
) : IGameService {
    override fun getRandomGameByCategory(category: Game.Category): Game {
        val minId = gameDAO.getMinIdByCategory(category)
        val maxId = gameDAO.getMaxIdByCategory(category)

        val randomId = (minId..maxId).random()

        return getGameById(randomId)
    }

    override fun getMockGame(category: Game.Category): Game {
        TODO("Not yet implemented")
    }

    override fun getGameById(id: Long): Game = gameDAO.findGameById(id) ?: throw GameNotFoundException(id)
}
