package com.example.demouiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.demouiapp.screens.HomeScreen
import com.example.demouiapp.screens.ScreenA
import com.example.demouiapp.screens.ScreenB
import com.example.demouiapp.screens.ScreenC
import com.example.demouiapp.screens.ScreenD
import com.example.demouiapp.screens.ScreenE

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("screen_a") { ScreenA(navController) }  // Truyền navController vào
        composable("screen_b") { ScreenB(navController) }  // Truyền navController vào
        composable("screen_c") { ScreenC(navController) }  // Truyền navController vào
        composable("screen_d") { ScreenD(navController) }  // Truyền navController vào
        composable("screen_e") { ScreenE(navController) }  // Truyền navController vào
    }
}