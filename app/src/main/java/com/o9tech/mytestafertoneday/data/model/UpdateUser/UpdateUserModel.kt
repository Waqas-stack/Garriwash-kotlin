package com.o9tech.mytestafertoneday.data.model.UpdateUser

import com.google.gson.annotations.SerializedName

data class UpdateUserModel(
    @SerializedName("@context")
    val context: String,
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val email: String,
    val fullName: String,
    @SerializedName("id")
    val idupdate: Int,
    val latitude: Double,
    val longitude: Double,
    val nationalIdentityNumber: String,
    val online: Int,
    val phone: String,
    val picture: String,
    val roles: List<String>,
    val state: Int
)