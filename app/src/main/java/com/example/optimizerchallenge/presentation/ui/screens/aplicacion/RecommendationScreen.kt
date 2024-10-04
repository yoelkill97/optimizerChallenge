package com.example.optimizerchallenge.presentation.ui.screens.aplicacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.optimizerchallenge.R
import com.example.optimizerchallenge.domain.entity.Resource
import com.example.optimizerchallenge.presentation.ui.components.LoadingScreen
import com.example.optimizerchallenge.presentation.ui.theme.Purple80
import com.example.optimizerchallenge.presentation.ui.theme.colorWhite

@Preview
@Composable
fun RecommendationPreview() {
   RecommendationResultComponent()
}

@Composable
fun RecommendationsPage(viewModel: ApplicationViewModel = hiltViewModel()){
    // Observar el estado de recomendaciones desde el ViewModel
    val recommendationState = viewModel.recommendationsState.collectAsState()

    // Llamada a la función que genera las recomendaciones al cargar la pantalla
    LaunchedEffect(Unit) {
        viewModel.getRecommendations()
    }

    // Llamar al componente que muestra el estado de recomendaciones
    RecommendationResultComponent(recommendationState.value)
}

@Composable
fun RecommendationResultComponent(recommendationState: Resource<String> = Resource.Idle) {
    // Definir el layout de la pantalla
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorWhite, // Set background color
        tonalElevation = 2.dp // Add elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (recommendationState) {
                is Resource.Idle, is Resource.Loading -> {
                    // Mostrar un mensaje de espera mientras se carga la información
                    Text(
                        text = "Cargando recomendaciones...",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                is Resource.Success -> {
                    SuccessMessageComponent(message = recommendationState.data)
                }

                is Resource.Error -> {
                    ErrorMessageComponent(message = recommendationState.message)
                }
            }

            LoadingScreen(isShowingDialog = recommendationState is Resource.Loading)
        }
    }
}

@Composable
fun SuccessMessageComponent(message: String) {
    // Componente personalizado para mostrar el mensaje de éxito
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Purple80, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = colorWhite,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun ErrorMessageComponent(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color(0xFFFFBABA), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFD8000C),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}