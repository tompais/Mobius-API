package com.coder_rangers.mobius_api.error.exceptions

class FinishedTestException(id: Long) : BadRequestException("The patient with id [$id] has finished the test. So, no more mental test games.")
