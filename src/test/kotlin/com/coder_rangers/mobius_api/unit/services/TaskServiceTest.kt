package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.coder_rangers.mobius_api.dao.interfaces.ITaskDAO
import com.coder_rangers.mobius_api.error.exceptions.TaskNotFoundException
import com.coder_rangers.mobius_api.services.implementations.TaskService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TaskServiceTest {
    @MockK
    private lateinit var taskDAO: ITaskDAO

    @InjectMockKs
    private lateinit var taskService: TaskService

    @Test
    fun getTaskByIdReturnsNotFoundTest() {
        // GIVEN
        every { taskDAO.getTaskById(any()) } returns null

        // THEN
        assertThat { taskService.getTaskById(5) }.isFailure().isInstanceOf(TaskNotFoundException::class)
    }
}
