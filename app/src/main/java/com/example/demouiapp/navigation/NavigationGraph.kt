package com.example.demouiapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.demouiapp.screens.HomeScreen
import com.example.demouiapp.screens.ScreenA
import com.example.demouiapp.screens.ScreenB
import com.example.demouiapp.screens.ScreenC
import com.example.demouiapp.screens.ScreenD
import com.example.demouiapp.screens.ScreenE
import com.example.demouiapp.screensextra.ScreenBExtra
import com.example.demouiapp.viewmodel.StudentViewModel

@Composable
fun NavigationGraph(navController: NavHostController, studentViewModel: StudentViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("screen_a") {
            // Khi màn hình ScreenA được mở, gọi hàm để tải 100 sinh viên
            LaunchedEffect(Unit) {
                studentViewModel.fetchFirst100Students()
            }
            ScreenA(navController, studentViewModel)
        }
        composable("screen_b") { ScreenB(navController) }  // Truyền navController vào
        composable("screen_c") { ScreenC(navController) }  // Truyền navController vào
        composable("screen_d") { ScreenD(navController) }  // Truyền navController vào
        composable("screen_e") { ScreenE(navController) }  // Truyền navController vào

        // Định nghĩa màn hình mới ScreenBExtra
        composable("screen_b_extra") {
            ScreenBExtra(navController)  // Chuyển đến màn hình mới
        }
    }
}