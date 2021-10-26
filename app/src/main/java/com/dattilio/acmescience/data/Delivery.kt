package com.dattilio.acmescience.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Delivery constructor(
    val destination: String,
    val driver: String,
)

data class DeliveryList constructor(val deliveries:List<Delivery>)