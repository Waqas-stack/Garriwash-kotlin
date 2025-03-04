package com.o9tech.mytestafertoneday.data.model.washoptionsmodels

import com.google.gson.annotations.SerializedName

data class HydraMember(
    @SerializedName("@id")
    val idhydra: String,
    @SerializedName("@type")
    val type: String,
    val id: Int,
    val name: String,
    val price: Price,
    val priceInCents: Int,
    val vehicleType: String
)