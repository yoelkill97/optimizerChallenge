package com.example.optimizerchallenge.presentation.ui.screens.aplicacion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optimizerchallenge.domain.entity.ApplicationDomData
import com.example.optimizerchallenge.domain.entity.Resource
import com.example.optimizerchallenge.domain.usecase.creation.CreateApplicationUseCase
import com.example.optimizerchallenge.domain.usecase.lista.GetAllApplicationUseCase
import com.example.optimizerchallenge.domain.usecase.recommendations.GetRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val createApplicationUseCase: CreateApplicationUseCase,
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val getApplicationsUseCase: GetAllApplicationUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow<Resource<Any>>(Resource.Idle)
    val registerState = _registerState.asStateFlow()

    private val _recommendationsState = MutableStateFlow<Resource<String>>(Resource.Idle)
    val recommendationsState = _recommendationsState.asStateFlow()

    private val _applicationsState = MutableStateFlow<Resource<List<ApplicationDomData>>>(Resource.Idle)
    val applicationsState = _applicationsState.asStateFlow()

    fun registerApplication(application: ApplicationDomData) {
        Log.d("apps", application.toString())
        viewModelScope.launch {
            try {
                _registerState.value = Resource.Loading
                val createApplication = withContext(Dispatchers.IO) { createApplicationUseCase.invoke(application) }
                _registerState.value = Resource.Success(createApplication)
            } catch (e: Exception) {
                _registerState.value = Resource.Error(e.message ?: "Error")
            }
        }
    }

    fun getRecommendations() {
        viewModelScope.launch {
            try {
                _recommendationsState.value = Resource.Loading
                val recommendations = withContext(Dispatchers.IO) { getRecommendationsUseCase.invoke() }
                _recommendationsState.value = Resource.Success(recommendations)
            } catch (e: Exception) {
                _recommendationsState.value = Resource.Error(e.message ?: "Error")
            }
        }
    }

    fun getApplications() {

        viewModelScope.launch {
            try {
                _applicationsState.value = Resource.Loading
                val applications = withContext(Dispatchers.IO) { getApplicationsUseCase.invoke() }
                _applicationsState.value = Resource.Success(applications.first())
            } catch (e: Exception) {
                _applicationsState.value = Resource.Error(e.message ?: "Error")
            }
        }
    }


    fun registerEventConsumed() {
        _registerState.value = Resource.Idle
    }
}