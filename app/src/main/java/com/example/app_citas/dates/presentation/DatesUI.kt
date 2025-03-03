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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_citas.dates.data.model.Date

@Composable
fun DatesScreen(dateViewModel: DateViewModel ) {

    val dates = dateViewModel.dates.observeAsState(emptyList())

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x002c2b))
    ){
        Text( text = "Tus Citas", fontSize = 50.sp)
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(dates.value) {
                date -> Date(date)
            }
        }

    }
}

@Composable
fun Date(date: Date) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(text = date.description, fontSize = 35.sp)
        Spacer( modifier = Modifier.height(10.dp))
        Text(text = date.description, fontSize = 25.sp)
        Spacer( modifier = Modifier.height(10.dp))
        Text(text = date.date.toString(), fontSize = 15.sp)
    }
}