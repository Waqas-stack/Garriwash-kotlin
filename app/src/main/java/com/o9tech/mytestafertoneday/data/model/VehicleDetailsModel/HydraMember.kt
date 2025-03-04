package com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel

import com.google.gson.annotations.SerializedName

data class HydraMember(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val activeStateIcon: String,
    val basePrice: BasePrice,
    val icon: String,
    val name: String,
    val washOptions: List<WashOption>
)