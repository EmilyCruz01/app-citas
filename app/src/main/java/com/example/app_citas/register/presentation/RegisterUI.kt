package com.example.app_citas.register.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.lifecycle.viewModelScope
import com.example.app_citas.register.data.model.RegisterRequest
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen ( registerViewModel: RegisterViewModel) {

    val name by registerViewModel.name.observeAsState("")
    val email by registerViewModel.email.observeAsState("")
    val password by registerViewModel.password.observeAsState("")
    val success by registerViewModel.success.observeAsState(true)

    Column (
        modifier = Modifier
            .background(Color(0xff002c2b))
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = name,
            onValueChange = { registerViewModel.onChangeName(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "ingresa tu nombre de usuario") },
            label = { Text( text = "Nombre de usuario", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Nombre de usuario",
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
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = email,
            onValueChange = { registerViewModel.onChangeEmail(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "ejemplo@gmail.com") },
            label = { Text( text = "Correo" , fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "email",
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

        if (!success) {
            Text( text = "Correo ya registrado", color = Color(0xffffbc11))
        }

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                registerViewModel.viewModelScope.launch {
                    registerViewModel.onSubmit(RegisterRequest(name, email, password))
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffff3d00),
            )
        ) {
            Text( text = "Registrarse")
        }

    }
}