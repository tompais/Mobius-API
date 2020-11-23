package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.models.Task

interface ITaskService {
    fun getTaskById(id: Long): Task
}
