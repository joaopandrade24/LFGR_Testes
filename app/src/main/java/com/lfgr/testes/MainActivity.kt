package com.lfgr.testes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lfgr.testes.ui.nav.BottomNavBar
import com.lfgr.testes.ui.nav.BottomNavItem
import com.lfgr.testes.ui.nav.MainNavHost
import com.lfgr.testes.ui.theme.LFGR_testesTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LFGR_testesTheme {
                val navController = rememberNavController()

                val items = listOf(
                    BottomNavItem.HomeButton,
                    BottomNavItem.ListButton,
                    BottomNavItem.MapButton,
                )

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
                        FloatingActionButton(onClick = { /* TODO */ }) {
                            Icon(Icons.Default.Add, contentDescription = "Adicionar")
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavHost(navController = navController)
                    }
                }
            }
        }
    }
}