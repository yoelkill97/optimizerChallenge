package com.example.optimizerchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.optimizerchallenge.presentation.navigation.NavigationGraph
import com.example.optimizerchallenge.presentation.ui.theme.OptimizerChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OptimizerChallengeTheme {
                NavigationGraph()
            }
        }
    }
}
