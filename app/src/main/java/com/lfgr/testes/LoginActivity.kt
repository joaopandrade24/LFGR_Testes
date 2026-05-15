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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lfgr.testes.ui.theme.LFGR_testesTheme


// Estes dois são fundamentais para o funcionamento do "by"
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Substitua 'LFGR_testesTheme' pelo nome do tema do seu projeto se for diferente
            LFGR_testesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Agora o 'innerPadding' existe e pode ser usado aqui:
                    LoginPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Preview(showBackground = true) // Passo 3: Habilita o preview [cite: 25, 72]
@Composable // Passo 3: Define que é um componente de UI [cite: 26, 71]
fun LoginPage(modifier: Modifier = Modifier) {
    // Estados para persistir os dados digitados [cite: 30, 31, 73, 74]
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity

    // Passo 6: Column com alinhamento centralizado [cite: 33, 87, 88]
    Column(
        modifier = modifier.padding(24.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Passo 5: Modifier para usar 90% da largura [cite: 83, 84, 85]
        val fieldModifier = Modifier.fillMaxWidth(0.9f)

        Text(text = "Bem-vindo/a!", fontSize = 24.sp) // [cite: 38, 39, 41]

        Spacer(modifier = Modifier.size(12.dp)) // Passo 7: Espaçador [cite: 90]

        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") }, // [cite: 45]
            onValueChange = { email = it }, // [cite: 47]
            modifier = fieldModifier
        )

        Spacer(modifier = Modifier.size(12.dp)) // [cite: 90]

        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite sua senha") }, // [cite: 52]
            onValueChange = { password = it }, // [cite: 54]
            visualTransformation = PasswordVisualTransformation(), // [cite: 57]
            modifier = fieldModifier
        )

        Spacer(modifier = Modifier.size(12.dp)) // [cite: 90]

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly // Passo 6 [cite: 89]
        ) {
            Button(
                onClick = {
                    // 1. Mostra o aviso na tela
                    Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()

                    // 2. Muda para a tela principal [cite: 143-145]
                    activity.startActivity(
                        Intent(activity, MainActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                },
                // Passo 8: Habilitado apenas se campos preenchidos [cite: 91, 95, 97]
                enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text("Login")
            }
            Button(onClick = { email = ""; password = "" }) { // Passo 2 (Limpar) [cite: 64, 76]
                Text("Limpar") // [cite: 65]
            }
        }
    }
}

