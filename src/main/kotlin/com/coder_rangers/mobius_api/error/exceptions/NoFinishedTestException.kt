package com.coder_rangers.mobius_api.error.exceptions

class NoFinishedTestException(id: Long) : BadRequestException("The patient [$id] has no finished the test.")
