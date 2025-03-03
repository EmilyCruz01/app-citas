package com.example.app_citas.newDate.presentation

import android.util.Log
import androidx.compose.material3.DatePickerState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_citas.login.presentation.LoginViewModel
import com.example.app_citas.newDate.data.model.NewDateRequest
import com.example.app_citas.newDate.domain.NewDateUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewDateViewModel(private val newDateUseCase: NewDateUseCase, private val loginViewModel: LoginViewModel, private val navigateToHome: () -> Unit) : ViewModel() {

    private val _title = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()
    private val _date = MutableLiveData<Date>()
    private val _dateLong = MutableLiveData<Long>()

    private val _showDatePicker = MutableLiveData<Boolean>()

    val showDatePicker: LiveData<Boolean> = _showDatePicker

    val title: LiveData<String> = _title
    val description: LiveData<String> = _description
    val dateLong: LiveData<Long?> = _dateLong
    val date: LiveData<Date> = _date
    
    fun onChangeTitle(title: String) {
        _title.value = title
    }

    fun onChangeDescription(des: String) {
        _description.value = des
    }

    fun setShowDatePicker(show: Boolean) {
        _showDatePicker.value = show
        Log.d("fecha", "mostrar fecha dialogo")
    }

    fun updateDate(dateMillis: Long) {
        _date.value = Date(dateMillis)
        _dateLong.value = dateMillis
//        _selectedDateMillis.value = dateMillis
    }

    suspend fun onSubmit() {
        Log.d("USERID", loginViewModel.userId.toString())
        val result = newDateUseCase(NewDateRequest(loginViewModel.userId ?: -1, title.value!!, description.value!!, dateLong.value!!))
        Log.d("API", result.toString())
        result.onSuccess {
            navigateToHome()
        }

    }


}