package com.dattilio.acmescience

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import com.dattilio.acmescience.deliveries.DeliveryListScreen
import com.dattilio.acmescience.ui.theme.AcmeScienceTheme
import com.dattilio.acmescience.deliveries.DeliveryListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: DeliveryListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcmeScienceTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val uiState = viewModel.uiStateFlow.collectAsState()
                    DeliveryListScreen(uiState.value)
                }
            }
        }
    }
}
