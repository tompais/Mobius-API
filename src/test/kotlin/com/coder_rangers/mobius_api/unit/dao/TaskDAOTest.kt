package com.coder_rangers.mobius_api.unit.dao

import assertk.assertThat
import assertk.assertions.isNull
import com.coder_rangers.mobius_api.dao.implementations.TaskDAO
import com.coder_rangers.mobius_api.database.repositories.ITaskRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull

@ExtendWith(MockKExtension::class)
class TaskDAOTest {
    @MockK
    private lateinit var taskRepository: ITaskRepository

    @InjectMockKs
    private lateinit var taskDAO: TaskDAO

    @Test
    fun getNullTaskById() {
        // GIVEN
        every { taskRepository.findByIdOrNull(any()) } returns null

        // WHEN
        val task = taskDAO.getTaskById(1L)

        // THEN
        assertThat(task).isNull()
    }
}
