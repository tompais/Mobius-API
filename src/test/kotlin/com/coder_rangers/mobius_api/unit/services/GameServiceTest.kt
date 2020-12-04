package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.coder_rangers.mobius_api.dao.interfaces.IGameDAO
import com.coder_rangers.mobius_api.error.exceptions.GameNotFoundException
import com.coder_rangers.mobius_api.models.Game.Category.ATTENTION
import com.coder_rangers.mobius_api.services.implementations.GameService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GameServiceTest {
    @MockK
    private lateinit var gameDAO: IGameDAO

    @InjectMockKs
    private lateinit var gameService: GameService

    @Test
    fun getRandomGameByCategoryTypeThrowsNotFoundTest() {
        // GIVEN
        every { gameDAO.getMaxIdByCategory(any(), isTestGame) } returns 2L
        every { gameDAO.getMinIdByCategory(any(), isTestGame) } returns 1L
        every { gameDAO.getGameById(any()) } returns null

        // THEN
        assertThat {
            gameService.getRandomGameByCategory(ATTENTION, isTestGame)
        }.isFailure().isInstanceOf(GameNotFoundException::class)
    }
}
