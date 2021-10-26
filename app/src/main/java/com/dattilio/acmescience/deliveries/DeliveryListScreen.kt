package com.dattilio.acmescience.deliveries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dattilio.acmescience.data.Delivery

@Composable
fun DeliveryListScreen(uiState: UiState) {
    when (uiState) {
        UiState.Empty -> EmptyScreen()
        UiState.Error -> ErrorScreen()
        UiState.Loading -> LoadingScreen()
        is UiState.Success -> SuccessScreen(uiState.employees)
    }

}

@Composable
fun LoadingScreen() {
    Column(Modifier.padding(16.dp)) {
        CircularProgressIndicator()
    }
}

@Composable
fun SuccessScreen(deliveries: List<Delivery>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(deliveries) { delivery -> DeliveryRow(delivery = delivery) }
    }
}

@Composable
fun DeliveryRow(delivery: Delivery) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = delivery.driver, style = MaterialTheme.typography.h4)
        Text(text = delivery.destination, style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ErrorScreen() {
    Column(Modifier.padding(16.dp)) {
        Text(text = "An error has occurred.")
    }
}

@Composable
fun EmptyScreen() {
    Column(Modifier.padding(16.dp)) {
        Text(text = "Server returned no employees.")
    }
}

@Composable
@Preview
fun PreviewSuccess() {
    DeliveryListScreen(
        UiState.Success(
            listOf(
                Delivery("Home", "Patrick")
            )
        )
    )
}

@Composable
@Preview
fun PreviewLoading() {
    DeliveryListScreen(UiState.Loading)
}

@Composable
@Preview
fun PreviewError() {
    DeliveryListScreen(UiState.Error)
}

@Composable
@Preview
fun PreviewEmpty() {
    DeliveryListScreen(UiState.Empty)
}


