package com.example.app_citas.dates.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_citas.dates.data.model.Date

@Composable
fun DatesScreen(dateViewModel: DateViewModel, navigateToNewDate: () -> Unit ) {

    val dates: List<Date> by dateViewModel.dates.observeAsState(emptyList())

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff002c2b))
            .padding( vertical = 50.dp, horizontal = 10.dp)
    ){
        Text( text = "Tus Citas", fontSize = 50.sp, color = Color(0xffffbc11))
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navigateToNewDate()
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffff3d00),
            )
        ) {
            Text(text = "Agregar una cita", color = Color(0xff002c2b))
        }

        LazyColumn {
            items(dates) {
                date -> Date(date)
            }
        }

    }

    LaunchedEffect(Unit) {
        if (dates.isEmpty()){
            dateViewModel.getDates()
        }
    }
}

@Composable
fun Date(date: Date) {
    Column(
        modifier = Modifier
            .background(Color(0xff076461))
            .padding(vertical = 30.dp, horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = date.title, fontSize = 35.sp, color = Color(0xffffbc11))
        Spacer( modifier = Modifier.height(10.dp))
        Text(text = date.description, fontSize = 25.sp, color = Color(0xff0a837f))
        Spacer( modifier = Modifier.height(10.dp))
        Text(text = date.date, fontSize = 15.sp, color = Color(0xff002c2b))
    }
    Spacer(modifier = Modifier.height(30.dp))

}