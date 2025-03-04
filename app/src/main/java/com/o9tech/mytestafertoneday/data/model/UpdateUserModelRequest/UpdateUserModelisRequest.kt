package com.o9tech.mytestafertoneday.data.model.UpdateUserModelRequest

data class UpdateUserModelisRequest(
    val email: String,
    val fullName: String,
    val latitude: Double,
    val longitude: Double,
    val nationalIdentityNumber: String,
    val password: String,
    val phone: String
)