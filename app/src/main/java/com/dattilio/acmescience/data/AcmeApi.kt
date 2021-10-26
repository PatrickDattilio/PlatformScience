package com.dattilio.acmescience.data

import retrofit2.Response
import retrofit2.http.GET


interface AcmeApi {

    @GET("endpoint.json")
    suspend  fun fetchDeliveryList(): Response<DeliveryList>


}