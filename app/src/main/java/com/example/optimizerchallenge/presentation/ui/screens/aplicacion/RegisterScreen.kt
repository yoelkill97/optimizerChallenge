package com.example.optimizerchallenge.presentation.ui.screens.aplicacion

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.optimizerchallenge.R
import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import com.example.optimizerchallenge.domain.entity.Resource
import com.example.optimizerchallenge.presentation.ui.components.CustomTextField
import com.example.optimizerchallenge.presentation.ui.components.LoadingScreen
import com.example.optimizerchallenge.presentation.ui.theme.colorWhite

@Preview
@Composable
fun RegisterPreview() {
    RegisterScreen()
}

@Composable
fun RegisterPage(viewModel: ApplicationViewModel = hiltViewModel()) {
    val registerState = viewModel.registerState.collectAsState()
    val recommendationsState = viewModel.recommendationsState.collectAsState()
    val context: Context = LocalContext.current
    LaunchedEffect(key1 = registerState.value) {
        when (val state = registerState.value) {
            is Resource.Success -> {
                Toast.makeText(context, "Registro de Aplicacion exitoso", Toast.LENGTH_LONG).show()
                viewModel.registerEventConsumed()
            }

            is Resource.Error -> {
                Toast.makeText(context, "Error: ${state.message}", Toast.LENGTH_LONG).show()
                viewModel.registerEventConsumed()
            }

            else -> {}
        }
    }
    LaunchedEffect(key1 = recommendationsState.value) {
        when (val state = recommendationsState.value) {
            is Resource.Success -> {
                Toast.makeText(context, "Reomendacion", Toast.LENGTH_LONG).show()
                viewModel.registerEventConsumed()
            }

            is Resource.Error -> {
                Toast.makeText(context, "Error: ${state.message}", Toast.LENGTH_LONG).show()
                viewModel.registerEventConsumed()
            }

            else -> {}
        }
    }
    RegisterScreen(
        onRegister = { apl -> viewModel.registerApplication(apl) },
        context = context,
        uiState = registerState.value
    )
}


@Composable
fun RegisterScreen(
    onRegister: (ApplicationDomData) -> Unit = { _ -> },
    context: Context = LocalContext.current,
    uiState: Resource<Any> = Resource.Idle,
) {
    var name by remember { mutableStateOf("") }
    var version by remember { mutableStateOf("") }
    var frequencyUsage by remember { mutableStateOf(0) }
    var lastUsedDays by remember { mutableStateOf(0) }
    var cpuUsage by remember { mutableStateOf(0) }
    var ramUsage by remember { mutableStateOf(0) }
    var isSupported by remember { mutableStateOf(false) }


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

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Registrar Aplicacion",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = rememberVectorPainter(Icons.Default.Settings),
                value = name,
                onValueChange = {
                    name = it
                },
                label = "Nombre "
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = rememberVectorPainter(Icons.Default.Settings),
                value = version,
                onValueChange = {
                    version = it
                },
                label = "Versión",
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = rememberVectorPainter(Icons.Default.Settings),
                value = frequencyUsage.toString(),
                keyBoarType = KeyboardType.Number,
                onValueChange = {
                    frequencyUsage = it.toIntOrNull() ?: 0
                },
                label = "Frecuencia de uso"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = rememberVectorPainter(Icons.Default.Settings),
                value = lastUsedDays.toString(),
                keyBoarType = KeyboardType.Number,
                onValueChange = {
                    lastUsedDays = it.toIntOrNull() ?: 0
                },
                label = "Dias desde el ultimo uso"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = rememberVectorPainter(Icons.Default.Settings),
                value = cpuUsage.toString(),
                keyBoarType = KeyboardType.Number,
                onValueChange = {
                    cpuUsage = it.toIntOrNull()?.coerceIn(0, 100) ?: 0
                },
                label = "Uso de CPU (%)"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = rememberVectorPainter(Icons.Default.Settings),
                value = ramUsage.toString(),
                keyBoarType = KeyboardType.Number,
                onValueChange = {
                    ramUsage = it.toIntOrNull()?.coerceIn(0, 100) ?: 0
                },
                label = "Uso de RAM (%)"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Soportado")
                Spacer(modifier = Modifier.weight(0.5f)) // Push the Switch to the right
                Switch(
                    checked = isSupported,
                    onCheckedChange = { isSupported = it }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))




            Spacer(modifier = Modifier.height(32.dp))


            var validationMessage by remember { mutableStateOf("") }
            fun validateInputs(): Boolean {
                return (name.isNotBlank() && version.isNotBlank() &&
                        frequencyUsage > 0 && lastUsedDays > 0 &&
                        cpuUsage in 0..100 && ramUsage in 0..100).apply {
                    if (!this) {
                        validationMessage = when {
                            name.isBlank() -> "Ingresa un nombre válido."
                            version.isBlank() -> "Ingresa una versión válida."
                            !version.matches(Regex("""\d+(\.\d+)*""")) -> "La versión debe estar en formato numérico, por ejemplo: 1.0, 2.1.3"
                            frequencyUsage <= 0 -> "Ingresa una frecuencia de uso válida (debe ser mayor a 0)."
                            lastUsedDays <= 0 -> "Ingresa los días desde el último uso (debe ser mayor a 0)."
                            cpuUsage !in 0..100 -> "Ingresa un uso de CPU válido (entre 0 y 100)."
                            ramUsage !in 0..100 -> "Ingresa un uso de RAM válido (entre 0 y 100)."
                            else -> "Todos los campos deben estar correctamente llenos."
                        }
                    }
                }
            }

            fun cleanInputs() {
                name = ""
                version = ""
                frequencyUsage = 0
                lastUsedDays = 0
                cpuUsage = 0
                ramUsage = 0
                isSupported = false
            }
            Button(
                onClick = {
                    if (!validateInputs()) {
                        Toast.makeText(
                            context,
                            validationMessage, Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }
                    // Crear el objeto Client y registrar
                    onRegister(
                        ApplicationDomData(
                            name,
                            version,
                            frequencyUsage,
                            lastUsedDays,
                            cpuUsage,
                            ramUsage,
                            isSupported
                        )
                    )
                    cleanInputs()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = "Registrar", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))


        }
        LoadingScreen(isShowingDialog = uiState is Resource.Loading)
    }
}