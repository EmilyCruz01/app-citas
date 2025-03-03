package com.example.app_citas.dates.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_citas.dates.data.model.Date
import com.example.app_citas.dates.domain.GetDatesUseCase
import com.example.app_citas.login.presentation.LoginViewModel
import kotlinx.coroutines.launch
import kotlin.math.log

class DateViewModel(private val getDatesUseCase: GetDatesUseCase, private val loginViewModel: LoginViewModel) : ViewModel() {

    private val _dates = MutableLiveData<List<Date>>()
    val dates: LiveData<List<Date>> = _dates

//    init {
//        viewModelScope.launch {
//            getDates()
//        }
//    }

    suspend fun getDates() {
        Log.d("USERID" , loginViewModel.userId.toString())
        val result = getDatesUseCase(loginViewModel.userId ?: -1)

        Log.d("API", result.toString())

        result.onSuccess { data ->
            _dates.value = data
        }

    }

}