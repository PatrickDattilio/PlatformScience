package com.dattilio.acmescience.deliveries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dattilio.acmescience.deliveries.DeliveryRepository
import com.dattilio.acmescience.deliveries.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryListViewModel @Inject constructor(deliveryRepository: DeliveryRepository) :
    ViewModel() {

    val uiStateFlow = MutableStateFlow<UiState>(UiState.Loading)
    var employeeJob: Job? = null

    init {
        employeeJob= viewModelScope.launch {
            uiStateFlow.emit(deliveryRepository.getDeliveries())
        }
    }

    override fun onCleared() {
        super.onCleared()
        employeeJob?.cancel()
    }
}
