package com.example.app_citas.newDate.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.app_citas.newDate.data.model.NewDateRequest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

//@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
//@Composable
//fun DatePickerExample() {
//
//    var selectedDate by remember { mutableStateOf<Long?>(null) }
//    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
//
//    // Texto que muestra la fecha seleccionada
//    val dateText = selectedDate?.let { dateFormatter.format(Date(it)) } ?: "Seleccionar fecha"
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//        // Campo de texto que muestra la fecha seleccionada
//        TextField(
//            value = dateText,
//            onValueChange = {},
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { showDatePicker = true },
//            readOnly = true
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Mostrar el DatePicker si showDatePicker es true
//        if (showDatePicker) {
//            DatePickerDialog(
//                onDismissRequest = { showDatePicker = false },
//                confirmButton = {
//                    Button(
//                        onClick = { showDatePicker = false }
//                    ) {
//                        Text("Aceptar")
//                    }
//                }
//            ) {
//                val datePickerState = rememberDatePickerState()
//                DatePicker(state = datePickerState)
//
//                // Cuando el usuario selecciona una fecha
//                LaunchedEffect(datePickerState.selectedDateMillis) {
//                    selectedDate = datePickerState.selectedDateMillis
//                }
//            }
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDateScreen(newDateViewModel: NewDateViewModel) {

    val title by newDateViewModel.title.observeAsState("")
    val description by newDateViewModel.description.observeAsState("")
    val date by newDateViewModel.date.observeAsState(Date())
    val dateLong by newDateViewModel.dateLong.observeAsState()

    val showDatePicker by newDateViewModel.showDatePicker.observeAsState(true)

    Column(
        modifier = Modifier
            .background(Color(0xff002c2b))
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = title,
            onValueChange = { newDateViewModel.onChangeTitle(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "ingresa un titulo") },
            label = { Text( text = "Titulo" , fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xffffbc11),
                focusedContainerColor = Color(0xffffbc11),
                unfocusedLabelColor = Color(0xff076461),
                focusedLabelColor = Color(0xff076461),
                focusedTextColor = Color(0xff002c2b),
                unfocusedTextColor = Color(0xff002c2b),
                focusedPlaceholderColor = Color(0xff0a837f),
                focusedIndicatorColor = Color(0xffff3d00)
            ),
        )
        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            value = description,
            onValueChange = { newDateViewModel.onChangeDescription(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "ingresa la descripcion") },
            label = { Text( text = "Descripcion" , fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xffffbc11),
                focusedContainerColor = Color(0xffffbc11),
                unfocusedLabelColor = Color(0xff076461),
                focusedLabelColor = Color(0xff076461),
                focusedTextColor = Color(0xff002c2b),
                unfocusedTextColor = Color(0xff002c2b),
                focusedPlaceholderColor = Color(0xff0a837f),
                focusedIndicatorColor = Color(0xffff3d00)
            ),
        )
        Spacer(modifier = Modifier.height(40.dp))

//        DatePickerTextField(
//            selectedDate = dateLong,
//            onDateSelected = { newDate ->
//                newDateViewModel.updateDate(newDate)
//            }
//        )

        TextField(
            value = date.toString(),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { newDateViewModel.setShowDatePicker(true) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xffffbc11),
                focusedContainerColor = Color(0xffffbc11),
                unfocusedLabelColor = Color(0xff076461),
                focusedLabelColor = Color(0xff076461),
                focusedTextColor = Color(0xff002c2b),
                unfocusedTextColor = Color(0xff002c2b),
                focusedPlaceholderColor = Color(0xff0a837f),
                focusedIndicatorColor = Color(0xffff3d00)
            ),
        )

        if (showDatePicker) {
            Text( text = "negro")
            DatePickerDialog(
                onDismissRequest = { newDateViewModel.setShowDatePicker(false) },
                confirmButton = {
                    Button(
                        onClick = { newDateViewModel.setShowDatePicker(false) }
                    ) {
                        Text("Aceptar")
                    }
                }
            ) {
                val datePickerState = rememberDatePickerState()
                DatePicker(state = datePickerState)

                LaunchedEffect(datePickerState.selectedDateMillis) {
                    newDateViewModel.updateDate(datePickerState.selectedDateMillis ?: 0)
                }
            }
        }

        Button(
            onClick = {
                newDateViewModel.viewModelScope.launch {
                    newDateViewModel.onSubmit()
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffff3d00),
            )
        ) {
            Text( text = "Guardar cita")
        }
    }

}

