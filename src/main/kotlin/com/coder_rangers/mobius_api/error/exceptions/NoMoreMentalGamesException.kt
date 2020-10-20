package com.coder_rangers.mobius_api.error.exceptions

class NoMoreMentalGamesException(id: Long) : BadRequestException("No more mental games for the patient [$id].")
