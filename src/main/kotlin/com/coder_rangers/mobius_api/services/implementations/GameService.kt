package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.error.exceptions.GameNotFoundException
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Game.Category.ORIENTATION
import com.coder_rangers.mobius_api.models.Input
import com.coder_rangers.mobius_api.models.Input.Type.TEXT
import com.coder_rangers.mobius_api.models.Task
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

    override fun getSpecificGameByCategory(category: Category): Game {
        return when (category) {
            ORIENTATION -> gameDAO.findGameByCategory(category)
            else -> Game(
                0,
                "Juego de memoria",
                "",
                ORIENTATION,
                listOf(
                    Task(
                        0, null, "¿Puede recordar las palabras que le dije antes?",
                        listOf(
                            Input(0, TEXT),
                            Input(0, TEXT),
                            Input(0, TEXT)
                        )
                    )
                )
            )
        }
    }

    private fun getGameById(id: Long): Game = gameDAO.findGameById(id) ?: throw GameNotFoundException(id)
}
