package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class WashPoint(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val name: String
)