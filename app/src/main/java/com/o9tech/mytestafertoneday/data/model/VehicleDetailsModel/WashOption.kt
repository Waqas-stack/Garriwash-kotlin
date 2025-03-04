package com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel

import com.google.gson.annotations.SerializedName

data class WashOption(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val name: String,
    val price: Price
)