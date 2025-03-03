package com.example.app_citas.dates.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_citas.dates.data.model.Date

class DateViewModel : ViewModel() {

    private val _dates = MutableLiveData<List<Date>>()
    val dates: LiveData<List<Date>> = _dates

}