package com.example.vkcontest.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vkcontest.ui.viewModel.ProductViewModel

@Composable
fun navigation(productViewModel: ProductViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            homeScreen(productViewModel = productViewModel, navController)
        }
        composable("productScreen/{id}", listOf(navArgument("id") {
            type = NavType.IntType
        })) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id")?.let { productScreen(it, productViewModel) }

        }
    }
}