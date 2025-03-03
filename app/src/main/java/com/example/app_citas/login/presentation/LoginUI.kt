package com.example.app_citas.login.presentation

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.lifecycle.viewModelScope
import com.example.app_citas.login.data.model.LoginRequest
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun LoginScreen (loginViewModel: LoginViewModel) {

    val success: Boolean by loginViewModel.success.observeAsState(true)
    val email: String by loginViewModel.email.observeAsState("")
    val password: String by loginViewModel.password.observeAsState("")

    Column (
        modifier = Modifier
            .background(Color(0xff002c2b))
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = email,
            onValueChange = { loginViewModel.onChangeEmail(it) },
            placeholder = { Text( text = "ejemplo@gmail.com") },
            label = { Text( text = "Correo electronico", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "correo electronico",
                    tint = Color(0xFFff3d00)
                )
            },
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
            value = password,
            onValueChange = { loginViewModel.onChangePassword(it) },
            placeholder = { Text( text = "ingresa tu contraseña aqui") },
            label = { Text( text = "Contraseña" , fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "candado",
                    tint = Color(0xffff3d00)
                )
            },
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "¿Aun no tienes cuenta?", color = Color(0xff076461), modifier = Modifier.clickable {
            loginViewModel.navigateToRegister()
        })

        if(!success){
            Spacer(modifier = Modifier.height(10.dp))
            Text( text= "correo o contrasena incorrecta", color = Color(0xffffbc11))
        }

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { loginViewModel.viewModelScope.launch {
                loginViewModel.onSubmit(LoginRequest(email, password))
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffff3d00),
            )
        ) {
            Text( text = "Iniciar sesion", color = Color(0xffffbc11))
        }

    }
}