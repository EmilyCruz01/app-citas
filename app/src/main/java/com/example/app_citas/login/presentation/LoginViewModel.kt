package com.example.app_citas.login.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.app_citas.login.data.model.LoginRequest
import com.example.app_citas.login.domain.LoginUseCase
import com.example.app_citas.register.presentation.RegisterScreen

class LoginViewModel(private val  loginUseCase: LoginUseCase, private val  navigateRegister: () -> Unit, private val navigateDates: () -> Unit) : ViewModel() {

    var userId by mutableStateOf<String?>(null)

    private val _success = MutableLiveData<Boolean>()
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val success: LiveData<Boolean> = _success

    fun onChangeEmail(email: String) {
        _email.value = email
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }

    suspend fun onSubmit(loginRequest: LoginRequest) {
        val result = loginUseCase(loginRequest)

        result.onSuccess {
            data -> (
                    if(data.success){
                        Log.d("login", "inicio de sesion correcto")
                        userId = data.user_id
                        navigateDates()
                    } else {
                        Log.d("login", "fallo en el login")
                        _success.value = false
                    }
                    )
        }
        result.onFailure {
            _success.value = false
        }

    }

    fun navigateToRegister() {
        navigateRegister()
    }

}