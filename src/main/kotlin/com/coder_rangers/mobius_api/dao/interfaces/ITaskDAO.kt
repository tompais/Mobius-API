package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Task

interface ITaskDAO {
    fun getTaskById(id: Long): Task?
}
