package com.example.aerohackatonnew

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.aerohackatonnew.screens.AeroSchemeScreen
import com.example.aerohackatonnew.screens.SearchFlightsScreen

@Composable
fun Navigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchFlightsScreen(navController)
        }
        composable("flight/{flightName}", arguments = listOf(navArgument("flightName") { type = NavType.StringType })
        ) { backStackEntry ->
            val flight = backStackEntry.arguments?.getString("flightName")
            AeroSchemeScreen(flight.toString())
        }
    }
}

sealed class NavigationItem(var route: String, var title: String) {
    object Search : NavigationItem("search","Search")
    object Flight : NavigationItem("flight", "Flight")
}