package com.example.app_citas.core.navigation

import android.app.Activity
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_citas.dates.domain.GetDatesUseCase
import com.example.app_citas.dates.presentation.DateViewModel
import com.example.app_citas.dates.presentation.DatesScreen
import com.example.app_citas.login.domain.LoginUseCase
import com.example.app_citas.login.presentation.LoginScreen
import com.example.app_citas.login.presentation.LoginViewModel
import com.example.app_citas.newDate.domain.NewDateUseCase
import com.example.app_citas.newDate.presentation.NewDateScreen
import com.example.app_citas.newDate.presentation.NewDateViewModel
import com.example.app_citas.register.domain.RegisterUseCase
import com.example.app_citas.register.presentation.RegisterScreen
import com.example.app_citas.register.presentation.RegisterViewModel
import kotlin.math.log

@Composable
fun NavigationWrapper () {

    val navController = rememberNavController()
    val loginViewModel = LoginViewModel(LoginUseCase(), {navController.navigate(Register)}, {navController.navigate(Dates)})

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen( loginViewModel)
        }
        composable<Register> {
            RegisterScreen(RegisterViewModel(RegisterUseCase(), {navController.navigate(Login)}))
        }
        composable<Dates> {
            DatesScreen(DateViewModel(GetDatesUseCase(), loginViewModel), {navController.navigate(NewDate)})
        }
        composable<NewDate> {
            NewDateScreen(NewDateViewModel(NewDateUseCase(), loginViewModel, {navController.navigate(Dates)} ))
        }
    }
}