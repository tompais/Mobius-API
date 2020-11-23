package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dao.interfaces.ITaskDAO
import com.coder_rangers.mobius_api.error.exceptions.TaskNotFoundException
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.services.interfaces.ITaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TaskService @Autowired constructor(
    private val taskDAO: ITaskDAO
) : ITaskService {
    override fun getTaskById(id: Long): Task =
        taskDAO.getTaskById(id) ?: throw TaskNotFoundException(id)
}
