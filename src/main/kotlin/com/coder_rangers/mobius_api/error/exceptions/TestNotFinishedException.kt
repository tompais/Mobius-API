package com.coder_rangers.mobius_api.error.exceptions

class TestNotFinishedException(id: Long) : BadRequestException("The patient with id [$id] has not finished the test.")
