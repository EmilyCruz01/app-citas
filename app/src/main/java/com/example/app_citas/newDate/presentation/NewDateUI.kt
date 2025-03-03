package com.example.app_citas.newDate.presentation


import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun RequestAudioPermission(onGranted: () -> Unit) {
    val context = LocalContext.current
    val permission = Manifest.permission.RECORD_AUDIO

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                onGranted()
            } else {
                Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Button(
        onClick = { launcher.launch(permission) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Permitir acceso al micrófono")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDateScreen(newDateViewModel: NewDateViewModel) {

    val title by newDateViewModel.title.observeAsState("")
    val description by newDateViewModel.description.observeAsState("")
    val date by newDateViewModel.date.observeAsState(Date())
    val dateLong by newDateViewModel.dateLong.observeAsState()

    var hasPermission by remember { mutableStateOf(false) }

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

        if (!hasPermission) {
            RequestAudioPermission { hasPermission = true }
        } else {
            Button(
                onClick = {
                    newDateViewModel.startListening()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffff3d00),
                )
            ) {
                Text( text = "dictar descripcion")
            }
        }

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

