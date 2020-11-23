package com.coder_rangers.mobius_api.error.exceptions

class TaskNotFoundException(id: Long) : NotFoundException("The task with id [$id] was not found.")
