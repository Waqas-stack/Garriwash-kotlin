package com.o9tech.mytestafertoneday.data.model.addPaymentModel

import com.google.gson.annotations.SerializedName

data class HydraMember(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val icon: String,
    val title: String
)