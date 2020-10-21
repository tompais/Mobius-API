package com.coder_rangers.mobius_api.error.exceptions

class GameNotFoundException(
    id: Long
) : NotFoundException(
    "There's no game with id [$id]."
)
