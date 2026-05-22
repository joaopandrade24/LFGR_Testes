package com.lfgr.testes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lfgr.testes.ui.theme.LFGR_testesTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// eu odeio kotlin

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LFGR_testesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    // persistencia
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity

    // coluna centralizada
    Column(
        modifier = modifier.padding(24.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val fieldModifier = Modifier.fillMaxWidth(0.9f)

        Text(text = "Bem-vindo/a!", fontSize = 24.sp)

        Spacer(modifier = Modifier.size(12.dp))
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            onValueChange = { email = it },
            modifier = fieldModifier
        )

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite sua senha") },
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = fieldModifier
        )

        Spacer(modifier = Modifier.size(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {

                    Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()

                    activity.startActivity(
                        Intent(activity, MainActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                },
                // verifica se campo n ta vazio
                enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text("Login")
            }
            Button(onClick = { email = ""; password = "" }) {
                Text("Limpar")
            }
        } // ROW

        Spacer(modifier = Modifier.size(24.dp))

        // botao tela registro
        Button(
            onClick = {
                activity.startActivity(Intent(activity, RegisterActivity::class.java))
            }
        ) {
            Text("Ainda não tem conta? Registre-se")
        }

    } // C O L U N A

} // função loginpage acaba aqui (cristo amado Kotlin tem muito parenteses e chave pqp)

