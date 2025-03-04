package com.o9tech.mytestafertoneday.data.model.addPaymentModel

import com.google.gson.annotations.SerializedName

data class AddPaymentMethodsModel(
    @SerializedName("@context")
    val context: String,
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("hydra:member")
    val hydramember: List<HydraMember>,
    @SerializedName("hydra:totalItems")
    val hydratotalItems: Int
)