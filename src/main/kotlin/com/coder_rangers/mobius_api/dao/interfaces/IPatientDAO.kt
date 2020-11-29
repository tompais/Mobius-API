package com.coder_rangers.mobius_api.dao.interfaces

import com.coder_rangers.mobius_api.models.Patient

interface IPatientDAO {
    fun getActivePatientByEmailAndPassword(email: String, password: String): Patient?
    fun findActivePatientById(id: Long): Patient?
    fun createOrUpdatePatient(patient: Patient): Patient
    fun getActivePatientByEmail(email: String): Patient?
}
