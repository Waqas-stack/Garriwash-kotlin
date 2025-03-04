package com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val formattedPrice: String,
    val priceInCents: Int
)