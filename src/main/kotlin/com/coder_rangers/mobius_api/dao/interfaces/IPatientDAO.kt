package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Patient

interface IPatientDAO {
    fun findActivePatientByEmailAndPassword(email: String, password: String): Patient?
    fun findActivePatientById(id: Long): Patient?
}
