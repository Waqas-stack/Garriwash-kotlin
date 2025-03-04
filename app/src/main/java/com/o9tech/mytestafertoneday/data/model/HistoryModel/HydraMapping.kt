package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class HydraMapping(
    @SerializedName("@type")
    val type: String,
    val `property`: String,
    val required: Boolean,
    val variable: String
)