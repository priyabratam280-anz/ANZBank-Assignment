package com.example.anzbankassignment.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.anzbankassignment.presentation.users.screens.UserDetailScreen
import com.example.anzbankassignment.presentation.users.screens.UsersScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users") {
        composable("users") {
            UsersScreen(onUserClick = { id -> navController.navigate("user/$id") })
        }
        composable(
            "user/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("userId") ?: ""
            UserDetailScreen(userId = id)
        }
    }
}