package com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel

import com.google.gson.annotations.SerializedName


data class VehicleDetailModel(
    @SerializedName("@context")
    val context: String,
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("hydra:member")
    val hydraMember: List<HydraMember>,
    @SerializedName("hydra:totalItems")
    val totalItems: Int
)











