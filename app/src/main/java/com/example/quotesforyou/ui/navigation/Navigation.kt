package com.example.quotesforyou.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quotesforyou.ui.navigation.NavigationConstants.NAV_HOME
import com.example.quotesforyou.ui.navigation.NavigationConstants.NAV_LOADEING
import com.example.quotesforyou.ui.screens.HomeScreen
import com.example.quotesforyou.ui.screens.loadingScreen

object NavigationConstants{
    const val NAV_LOADEING = "NAV_LOADING"
    const val NAV_HOME = "NAV_HOME"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NAV_LOADEING) {
        composable(NAV_LOADEING){
            loadingScreen {
                navController.navigate(NAV_HOME)
            }
        }

        composable(NAV_HOME) {
            HomeScreen()
        }
    }
}