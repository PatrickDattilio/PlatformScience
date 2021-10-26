package com.dattilio.acmescience.deliveries

import com.dattilio.acmescience.data.Delivery


sealed class UiState() {
    data class Success(val employees: List<Delivery>) : UiState()
    object Loading : UiState()
    object Empty : UiState()
    object Error : UiState()
}
