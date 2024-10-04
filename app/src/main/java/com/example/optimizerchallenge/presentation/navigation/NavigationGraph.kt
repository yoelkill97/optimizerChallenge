package com.example.optimizerchallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.optimizerchallenge.presentation.ui.screens.SplashScreen
import com.example.optimizerchallenge.presentation.ui.screens.aplicacion.ApplicationsPage
import com.example.optimizerchallenge.presentation.ui.screens.aplicacion.ApplicationsScreen
import com.example.optimizerchallenge.presentation.ui.screens.aplicacion.RecommendationsPage
import com.example.optimizerchallenge.presentation.ui.screens.aplicacion.RegisterPage
import com.example.optimizerchallenge.presentation.ui.screens.main.MainPage

@Composable
fun NavigationGraph (
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate(Screens.MainScreen.route) {
                        popUpTo(it.destination.id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = Screens.MainScreen.route){
            MainPage(
                onRegister = {
                    navController.navigate(Screens.RegisterScreen.route){
                        launchSingleTop = true
                    }
                },
                        onListApplications = {
                    navController.navigate(Screens.ListScreen.route){
                        launchSingleTop = true
                    }
                },
                onGetRecommendations = {
                    navController.navigate(Screens.RecommendationScreen.route){
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = Screens.RegisterScreen.route) {
            RegisterPage()
        }
        composable(route = Screens.ListScreen.route) {
            ApplicationsPage(onRegisterClick = {
                navController.navigate(Screens.RegisterScreen.route){
                    launchSingleTop = true
                }
            })
        }
        composable(route = Screens.RecommendationScreen.route) {
            RecommendationsPage()
        }
    }
}