package com.coder_rangers.mobius_api.dao.implementations

import com.coder_rangers.mobius_api.dao.interfaces.ITaskDAO
import com.coder_rangers.mobius_api.database.repositories.ITaskRepository
import com.coder_rangers.mobius_api.models.Task
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class TaskDAO(
    private val taskRepository: ITaskRepository
) : ITaskDAO {
    override fun getTaskById(id: Long): Task? = taskRepository.findByIdOrNull(id)
}
