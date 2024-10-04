package com.example.optimizerchallenge.presentation.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens(route = "splash_screen")
    object RegisterScreen : Screens(route = "register_screen")
    object ListScreen : Screens(route = "list_screen")
    object RecommendationScreen : Screens(route = "recommendation_screen")
    object MainScreen : Screens(route = "main_screen")
}