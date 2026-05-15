package com.lfgr.testes

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lfgr.testes.ui.theme.LFGR_testesTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LFGR_testesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    // persistencia, campos de digitacao
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val activity = context as Activity

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val fieldModifier = Modifier.fillMaxWidth(0.9f)

        Text(text = "Crie sua conta", fontSize = 24.sp)
        Spacer(modifier = Modifier.size(12.dp))

        // campos de digitacao
        OutlinedTextField(value = name, label = { Text("Nome") }, onValueChange = { name = it }, modifier = fieldModifier)
        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(value = email, label = { Text("E-mail") }, onValueChange = { email = it }, modifier = fieldModifier)
        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(value = password, label = { Text("Senha") }, onValueChange = { password = it }, visualTransformation = PasswordVisualTransformation(), modifier = fieldModifier)
        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(value = repeatPassword, label = { Text("Repetir Senha") }, onValueChange = { repeatPassword = it }, visualTransformation = PasswordVisualTransformation(), modifier = fieldModifier)
        Spacer(modifier = Modifier.size(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = {
                    Toast.makeText(context, "Registro OK!", Toast.LENGTH_LONG).show()
                    activity.finish()
                },
                // verifica se ta igual e n ta vazio
                enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password == repeatPassword
            ) {
                Text("Registrar") // [cite: 161]
            }

            Button(onClick = {
                name = ""; email = ""; password = ""; repeatPassword = ""
            }) {
                Text("Limpar")
            }
        }
    }
}