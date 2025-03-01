package com.example.app_citas.register.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen ( registerViewModel: RegisterViewModel) {

    val name by registerViewModel.name.observeAsState("")
    val email by registerViewModel.email.observeAsState("")
    val password by registerViewModel.password.observeAsState("")

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x002c2b))
    ) {

        TextField(
            value = name,
            onValueChange = {  },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "ingresa tu nombre de usuario") },
            label = { Text( text = "Nombre de usuario", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Nombre de usuario",
                    tint = Color(0xFFB6B6B6)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF535353),
                focusedContainerColor = Color(0xFF535353),
                unfocusedLabelColor = Color(0xFFB6B6B6),
                focusedLabelColor = Color(0xFF068D9C),
                focusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFB6B6B6),
                focusedIndicatorColor = Color(0xFFB6B6B6)
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = email,
            onValueChange = { registerViewModel.onChangePassword(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "ejemplo@gmail.com") },
            label = { Text( text = "Correo" , fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "email",
                    tint = Color(0xFFB6B6B6)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF535353),
                focusedContainerColor = Color(0xFF535353),
                unfocusedLabelColor = Color(0xFFB6B6B6),
                focusedLabelColor = Color(0xFF068D9C),
                focusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFB6B6B6),
                focusedIndicatorColor = Color(0xFFB6B6B6)
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password,
            onValueChange = { registerViewModel.onChangePassword(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "ingresa tu contrasena") },
            label = { Text( text = "Contrasena" , fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "contrasena",
                    tint = Color(0xFFB6B6B6)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF535353),
                focusedContainerColor = Color(0xFF535353),
                unfocusedLabelColor = Color(0xFFB6B6B6),
                focusedLabelColor = Color(0xFF068D9C),
                focusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFB6B6B6),
                focusedIndicatorColor = Color(0xFFB6B6B6)
            ),
        )

    }
}