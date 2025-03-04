package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class HydraSearch(
    @SerializedName("@type")
    val type: String,
    @SerializedName("hydra:mapping")
    val hydramapping: List<HydraMapping>,
    @SerializedName("hydra:template")
    val hydratemplate: String,
    @SerializedName("hydra:variableRepresentation")
    val hydravariableRepresentation: String
)