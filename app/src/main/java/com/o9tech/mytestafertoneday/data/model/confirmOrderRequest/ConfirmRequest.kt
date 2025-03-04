package com.o9tech.mytestafertoneday.data.model.confirmOrderRequest

data class ConfirmRequest(
    val carNumber: String,
    val vehicleType: String,
    val washOptions: List<String>,
    val paymentMethod: String,
    val location: Location,
    val scheduledAt: String,
    val washPoint: String
)

data class Location(
    val address: String,
    val latitude: String,
    val longitude: String
)

