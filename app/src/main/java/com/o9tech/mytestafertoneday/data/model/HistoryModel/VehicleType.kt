package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class VehicleType(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val activeStateIcon: String,
    val basePriceInCents: Int,
    val icon: String,
    val name: String,
    val washOptions: List<WashOption>
)