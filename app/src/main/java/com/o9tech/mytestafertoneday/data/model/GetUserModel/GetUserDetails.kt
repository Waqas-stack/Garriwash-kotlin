package com.o9tech.mytestafertoneday.data.model.GetUserModel

import com.google.gson.annotations.SerializedName

data class GetUserDetails(
    @SerializedName("@context")
    val context: String,
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val email: String,
    val fullName: String,
    @SerializedName("id")
    val idis: Int,
    val latitude: Double,
    val longitude: Double,
    val nationalIdentityNumber: String,
    val online: Int,
    val phone: String,
    val picture: String,
    val roles: List<String>,
    val state: Int
)