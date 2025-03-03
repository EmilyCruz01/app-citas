package com.example.app_citas.newDate.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_citas.login.presentation.LoginViewModel
import com.example.app_citas.newDate.data.model.NewDateRequest
import com.example.app_citas.newDate.domain.NewDateUseCase
import java.util.Date
import java.util.Locale

class NewDateViewModel(private val newDateUseCase: NewDateUseCase, private val loginViewModel: LoginViewModel, private val navigateToHome: () -> Unit, private val context: Context) : ViewModel() {

    private val _speechRecognizer = MutableLiveData<SpeechRecognizer>()
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

    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla ahora...")
    }


    fun startListening() {
        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(speechListener)
        speechRecognizer.startListening(intent)
    }

    val speechListener = object : RecognitionListener {
        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                _description.value = matches[0] // Usa el primer resultado
            }
        }

        override fun onError(error: Int) {
            Log.e("Speech", "Error en reconocimiento de voz: $error")
        }

        // Métodos vacíos que se deben sobreescribir
        override fun onReadyForSpeech(params: Bundle?) {}
        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray?) {}
        override fun onEndOfSpeech() {}
        override fun onPartialResults(partialResults: Bundle?) {}
        override fun onEvent(eventType: Int, params: Bundle?) {}
    }


}