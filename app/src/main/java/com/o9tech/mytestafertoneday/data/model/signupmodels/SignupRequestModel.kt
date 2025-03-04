package com.o9tech.mytestafertoneday.data.model.signupmodels

data class SignupRequestModel(
    val email: String,
    val roles: List<String> = listOf("CUSTOMER"),
    val password: String,
    val fullName: String,
    val phone: String,
    val picture: String = "string",
    val nationalIdentityNumber: String,
    val type: String = "CUSTOMER",
    val state: Int = 1,
    val latitude: Int = 0,
    val longitude: Int = 0,
    val online: Int = 0
)