package com.coder_rangers.mobius_api.error.exceptions

class PatientNotFoundException : NotFoundException {
    constructor() : super("Patient not found. Please, check your email and password.")
    constructor(id: Long) : super("Patient with id [$id] was not found")
}
