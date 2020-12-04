package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.error.exceptions.GameNotFoundException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.services.interfaces.IGameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GameService @Autowired constructor(
    private val gameDAO: IGameDAO
) : IGameService {
    override fun getRandomGameByCategory(category: Category): Game {
        val minId = gameDAO.getMinIdByCategory(category)
        val maxId = gameDAO.getMaxIdByCategory(category)

        val randomId = (minId..maxId).random()

        return getGameById(randomId)
    }

    override fun getGameById(id: Long): Game = gameDAO.getGameById(id) ?: throw GameNotFoundException(id)

    override fun getNotTestCategories(): List<Category> = gameDAO.getNotTestCategories()
}
