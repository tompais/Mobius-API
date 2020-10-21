package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.error.exceptions.GameNotFoundException
import com.coder_rangers.mobius_api.models.Category
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
    override fun getRandomGameByCategoryType(categoryType: Category.Type): Game {
        val minId = gameDAO.getMinIdByCategoryType(categoryType)
        val maxId = gameDAO.getMaxIdByCategoryType(categoryType)

        val randomId = (minId..maxId).random()

        return getGameById(randomId)
    }

    override fun getMockGame(nextGameCategory: Category.Type): Game {
        TODO("Not yet implemented")
    }

    private fun getGameById(id: Long): Game = gameDAO.findGameById(id) ?: throw GameNotFoundException(id)
}
