package com.coder_rangers.mobius_api.error.exceptions

class NoResultNotFoundException(id: Long) : NotFoundException("Patient with id [$id] has no test result.")
