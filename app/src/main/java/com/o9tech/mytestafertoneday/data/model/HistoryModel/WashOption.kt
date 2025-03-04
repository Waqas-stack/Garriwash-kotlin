package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class WashOption(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val name: String,
    val priceInCents: Int
)