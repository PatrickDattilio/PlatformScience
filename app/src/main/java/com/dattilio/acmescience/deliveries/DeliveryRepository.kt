package com.dattilio.acmescience.deliveries

import com.dattilio.acmescience.data.AcmeApi
import com.dattilio.acmescience.data.Delivery
import com.dattilio.acmescience.data.DeliveryList
import retrofit2.Response
import javax.inject.Inject

class DeliveryRepository @Inject constructor(private val acmeApi: AcmeApi) {

    val deliveries = DeliveryList(
        listOf(
            Delivery("Everardo Welch", "215 Osinski Manors"),
            Delivery("Orval Mayert", "9856 Marvin Stravenue"),
            Delivery("Howard Emmerich", "7127 Kathlyn Ferry"),
            Delivery("Izaiah Lowe", "987 Champlin Lake"),
            Delivery("Monica Hermann", "63187 Volkman Garden Suite 447"),
            Delivery("Ellis Wisozk", "75855 Dessie Lights"),
            Delivery("Noemie Murphy", "1797 Adolf Island Apt. 744"),
            Delivery("Cleve Durgan", "2431 Lindgren Corners"),
            Delivery("Murphy Mosciski", "8725 Aufderhar River Suite 859"),
            Delivery("Kaiser Sose", "79035 Shanna Light Apt. 322"),
        )
    )

    suspend fun getDeliveries(): UiState {
        val response = Response.success(deliveries)
        //acmeApi.fetchDeliveryList()
        return if (response.isSuccessful) {

            response.body()?.let {
                UiState.Success(it.deliveries)
            } ?: UiState.Empty
        } else {
            UiState.Error
        }
    }
}