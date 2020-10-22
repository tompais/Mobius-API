package com.coder_rangers.mobius_api.requests

class TaskAnswer<T>(
    val taskId: Long,

    val answers: List<T>
)
