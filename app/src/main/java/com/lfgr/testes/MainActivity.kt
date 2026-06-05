package com.lfgr.testes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lfgr.testes.ui.nav.BottomNavBar
import com.lfgr.testes.ui.nav.BottomNavItem
import com.lfgr.testes.ui.nav.MainNavHost
import com.lfgr.testes.ui.theme.LFGR_testesTheme
import com.lfgr.testes.ui.CityDialog
import com.lfgr.testes.model.MainViewModel // Import atualizado!

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LFGR_testesTheme {
                var showDialog by remember { mutableStateOf(false) }
                val navController = rememberNavController()

                val items = listOf(
                    BottomNavItem.HomeButton,
                    BottomNavItem.ListButton,
                    BottomNavItem.MapButton,
                )

                if (showDialog) {
                    CityDialog(
                        onDismiss = { showDialog = false },
                        onConfirm = { city ->
                            if (city.isNotBlank()) {
                                viewModel.add(city)
                            }
                            showDialog = false
                        }
                    )
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Bem-vindo/a!") },
                            actions = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                        contentDescription = "Sair do App"
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavBar(navController = navController, items = items)
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { showDialog = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Adicionar")
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        // Agora o MainNavHost está recebendo o viewModel corretamente
                        MainNavHost(navController = navController, viewModel = viewModel)
                    }
                }
            }
        }
    }
}