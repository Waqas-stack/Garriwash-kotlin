package com.o9tech.mytestafertoneday.data.model.signupmodels

import com.google.gson.annotations.SerializedName

data class SignUpResponseModel(
    @SerializedName("@context")
    val context: String,
    @SerializedName("@id")
    val idUrl: String,
    @SerializedName("@type")
    val type: String,
    val id: Int,
    val email: String,
    val roles: List<String>,
    val fullName: String,
    val phone: String,
    val picture: String,
    val nationalIdentityNumber: String,
    val state: Int,
    val latitude: Double,
    val longitude: Double,
    val online: Int
)
