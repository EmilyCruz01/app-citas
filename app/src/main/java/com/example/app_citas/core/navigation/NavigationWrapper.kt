package com.example.app_citas.core.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_citas.login.presentation.LoginScreen
import com.example.app_citas.login.presentation.LoginViewModel

@Composable
fun NavigationWrapper (activity: Activity) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen( LoginViewModel())
        }
    }
}