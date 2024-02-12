package com.rodrigocopetti.thedogshow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rodrigocopetti.thedogshow.screens.BreedDetailsScreen
import com.rodrigocopetti.thedogshow.screens.BreedListScreen

/**
 * TheDogShow navigation component
 * All navigation segues must be defined here
 */
@Composable
fun DogsRootNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "BreedList") {
        composable(route = "BreedList") {
            BreedListScreen(navController = navController)
        }
        composable(
            route = "BreedDetails/{breedName}?breedImages={breedImages}",
            arguments = listOf(
                navArgument("breedName") { type = NavType.StringType },
                navArgument("breedImages") { type = NavType.StringArrayType }
            )
        ) { backStackEntry ->
            BreedDetailsScreen(
                navController = navController,
                breedName = backStackEntry.arguments?.getString("breedName") ?: "the selected breed",
                breedImages = backStackEntry.arguments?.getStringArray("breedImages") ?: emptyArray()
            )
        }
    }
}

/**
 * Helper functions to better integrate with Android's APIs
 */
fun List<*>.toNavArgs(key: String) : String = buildString {
    this@toNavArgs.forEachIndexed{ i, item ->
        if(i > 0) append("&")
        append("$key=$item")
    }
}