package com.o9tech.mytestafertoneday.data.model.loginmodels

data class User(
    val email: String,
    val fullName: String,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val nationalIdentityNumber: String,
    val online: Int,
    val phone: String,
    val picture: String,
    val roles: List<String>,
    val state: Int
)