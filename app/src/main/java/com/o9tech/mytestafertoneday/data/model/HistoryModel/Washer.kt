package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class Washer(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val fullName: String,
    val latitude: Double,
    val longitude: Double,
    val picture: Any
)