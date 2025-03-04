package com.o9tech.mytestafertoneday.data.model.HistoryModel

import com.google.gson.annotations.SerializedName

data class HydraMember(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    val carNumber: String,
    val createdAt: String,
    val customer: Customer,
    val location: Location,
    val paymentMethod: PaymentMethod,
    val scheduledAt: String,
    val state: Int,
    val tipAmountInCents: Int,
    @SerializedName("type")
    val typetwo: String,
    val vehicleType: VehicleType,
    val washPoint: WashPoint,
    val washer: Washer
)