package com.o9tech.mytestafertoneday.data.model.washoption

import com.google.gson.annotations.SerializedName

data class WashpointModel(
    @SerializedName("@context")
    val context: String,
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("hydra:member")
    val hydraa: List<HydraMember>,
    @SerializedName("hydra:totalItems")
    val hydrato: Int
)