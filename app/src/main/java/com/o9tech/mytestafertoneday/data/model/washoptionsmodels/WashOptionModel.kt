package com.o9tech.mytestafertoneday.data.model.washoptionsmodels

import com.google.gson.annotations.SerializedName

data class WashOptionModel(
    @SerializedName("@context")
    val context: String,
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("hydra:member")
    val hydramem: List<HydraMember>,
    @SerializedName("hydra:totalItems")
    val hydratotalItems: Int
)