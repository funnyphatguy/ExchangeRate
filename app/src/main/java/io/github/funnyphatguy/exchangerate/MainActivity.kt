package io.github.funnyphatguy.exchangerate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import io.github.funnyphatguy.exchangerate.presentation.navigation.ExchangeRateApp
import io.github.funnyphatguy.exchangerate.ui.theme.ExchangeRateTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExchangeRateTheme {
                ExchangeRateApp()
            }
        }
    }
}

