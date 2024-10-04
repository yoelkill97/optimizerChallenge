package com.example.optimizerchallenge.presentation.ui.screens.aplicacion

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.optimizerchallenge.R
import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import com.example.optimizerchallenge.domain.entity.Resource
import com.example.optimizerchallenge.presentation.ui.components.LoadingScreen
import com.example.optimizerchallenge.presentation.ui.theme.Purple80
import com.example.optimizerchallenge.presentation.ui.theme.colorWhite

@Preview
@Composable
fun ApplicationsScreenPreview() {
    ApplicationsScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationsPage(
    viewModel: ApplicationViewModel = hiltViewModel(),
    onRegisterClick: () -> Unit = {}
) {
    val applicationsState = viewModel.applicationsState.collectAsState()

    // Cargar las aplicaciones al inicio
    LaunchedEffect(Unit) {
        viewModel.getApplications()
    }

    ApplicationsScreen(applicationsState.value, onRegisterClick)


}

@Composable
fun ApplicationsScreen(
    applicationsState: Resource<List<ApplicationDomData>> = Resource.Idle,
    onRegister: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorWhite, // Set background color
        tonalElevation = 2.dp // Add elevation
    ) {
        // Usamos un Box para manejar la disposición en pantalla completa
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp) // Añadimos un padding en la parte superior para la imagen
            ) {
                Spacer(modifier = Modifier.height(42.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo_app),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp) // Padding horizontal para ajustar la imagen
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Contenedor de la lista y el contenido principal
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp), // Padding en los laterales
                    verticalArrangement = Arrangement.SpaceBetween // Acomoda los elementos con espacio entre ellos
                ) {
                    when (applicationsState) {
                        is Resource.Idle -> {
                        }

                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            val applications = applicationsState.data
                            if (applications.isEmpty()) {
                                // Mensaje cuando la lista está vacía
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = "No hay aplicaciones registradas.")
                                    Spacer(modifier = Modifier.height(32.dp))
                                    Button(
                                        onClick = {
                                            onRegister()
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(56.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                        ),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            text = "Registrar Nueva Aplicacion",
                                            color = Color.White
                                        )
                                    }
                                }
                            } else {
                                // Mostrar la lista de aplicaciones usando LazyColumn
                                LazyColumn(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    items(applications.size) { index ->
                                        ApplicationItemComponent(applications[index])
                                    }
                                }
                            }
                        }

                        is Resource.Error -> {
                            // Mostrar mensaje de error con un botón para registrar nueva aplicación
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = applicationsState.message,
                                    color = Color.Red,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
            }
            LoadingScreen(isShowingDialog = applicationsState is Resource.Loading)
        }
    }
}

@Composable
fun ApplicationItemComponent(app: ApplicationDomData) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Purple80)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Nombre: ${app.name}",
                style = MaterialTheme.typography.labelLarge,
                color = colorWhite,
            )
            Text(
                text = "Versión: ${app.version}",
                style = MaterialTheme.typography.labelLarge,
                color = colorWhite,
            )
            Text(
                text = "Frecuencia de Uso: ${app.frequencyUsage} veces/mes",
                style = MaterialTheme.typography.labelLarge,
                color = colorWhite,
            )
            Text(
                text = "Último Uso: hace ${app.lastUsedDays} días",
                style = MaterialTheme.typography.labelLarge,
                color = colorWhite,
            )
            Text(
                text = "Consumo de CPU: ${app.cpuUsage}%",
                style = MaterialTheme.typography.labelLarge,
                color = colorWhite,
            )
            Text(
                text = "Consumo de RAM: ${app.ramUsage}%",
                style = MaterialTheme.typography.labelLarge,
                color = colorWhite,
            )
            Text(
                text = "Soportada: ${if (app.isSupported) "Sí" else "No"}",
                style = MaterialTheme.typography.labelLarge,
                color = colorWhite,
            )
        }
    }
}