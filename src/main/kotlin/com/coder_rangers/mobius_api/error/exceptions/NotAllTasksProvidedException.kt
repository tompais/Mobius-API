package com.coder_rangers.mobius_api.error.exceptions

class NotAllTasksProvidedException(gameId: Long) : BadRequestException("Not all tasks for game [$gameId] were provided")
