package com.coder_rangers.mobius_api.dao.implementations

import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.database.repositories.IGameRepository
import com.coder_rangers.mobius_api.models.Category
import com.coder_rangers.mobius_api.models.Game
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class GameDAO @Autowired constructor(
    private val gameRepository: IGameRepository
) : IGameDAO {
    override fun getMaxIdByCategoryType(categoryType: Category.Type): Long =
        gameRepository.getMaxIdByCategoryType(categoryType)

    override fun getMinIdByCategoryType(categoryType: Category.Type): Long =
        gameRepository.getMinIdByCategoryType(categoryType)

    override fun findGameById(id: Long): Game? = gameRepository.findByIdOrNull(id)
}
