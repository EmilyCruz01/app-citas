package com.example.app_citas.core.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_citas.dates.presentation.DateViewModel
import com.example.app_citas.dates.presentation.DatesScreen
import com.example.app_citas.login.domain.LoginUseCase
import com.example.app_citas.login.presentation.LoginScreen
import com.example.app_citas.login.presentation.LoginViewModel
import com.example.app_citas.register.domain.RegisterUseCase
import com.example.app_citas.register.presentation.RegisterScreen
import com.example.app_citas.register.presentation.RegisterViewModel

@Composable
fun NavigationWrapper () {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen( LoginViewModel(LoginUseCase(), {navController.navigate(Register)}, {navController.navigate(Dates)}))
        }
        composable<Register> {
            RegisterScreen(RegisterViewModel(RegisterUseCase(), {navController.navigate(Login)}))
        }
        composable<Dates> {
            DatesScreen(DateViewModel())
        }
    }
}