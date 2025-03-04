package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class AllHistoyModel(
    @SerializedName("@context")
    val context: String,
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("hydra:member")
    val hydraorder: List<HydraMember>,
    @SerializedName("hydra:search")
    val hydrasearch: HydraSearch,
    @SerializedName("hydra:totalItems")
    val hydratotalItems: Int
)