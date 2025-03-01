package com.example.app_citas.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val name: LiveData<String> = _name
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    fun onChangeEmail (email: String) {
        _email.value = email
    }

    fun onChangeName (name: String) {
        _name.value = name
    }

    fun onChangePassword (password: String) {
        _password.value = password
    }
}