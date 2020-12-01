package com.coder_rangers.mobius_api.error.exceptions

class PatientIsAGuardianException(email: String) :
    BadRequestException("The selected guardian with email [$email] is actually a patient.")
