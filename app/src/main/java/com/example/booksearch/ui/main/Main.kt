package com.example.booksearch.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booksearch.ui.screens.ResultsScreen
import com.example.booksearch.ui.screens.HomeScreen

@Composable
fun MainLandingScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen { title ->
                navController.navigate("${NavScreen.ResultsScreen.route}/$title")
            }
        }
        composable(
            route = NavScreen.ResultsScreen.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.ResultsScreen.argument0) { type = NavType.StringType }
            )
        ) {
            ResultsScreen(viewModel = hiltViewModel())
        }
    }

}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object ResultsScreen : NavScreen("ResultsScreen") {
        const val routeWithArgument: String = "ResultsScreen/{title}"
        const val argument0: String = "title"
    }
}