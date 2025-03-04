package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class PaymentMethod(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val icon: String,
    val title: String
)