package com.ulyanenko.userinfo.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ulyanenko.userinfo.presentation.main.repositories.UserRepositoriesScreen
import com.ulyanenko.userinfo.presentation.main.users.UserList

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "users") {
        composable("users") {
            UserList(
                onUserClickListener = { user ->
                    navController.navigate("repositories/${user.login}")
                }
            )
        }
        composable(
            route = "repositories/{userLogin}",
            arguments = listOf(navArgument("userLogin") { type = NavType.StringType })
        ) { backStackEntry ->
            val userLogin = backStackEntry.arguments?.getString("userLogin") ?: ""
            UserRepositoriesScreen(userLogin)
        }
    }
}
