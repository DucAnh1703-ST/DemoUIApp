package com.example.demouiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demodatabase.IMyMySchoolInterface
import com.example.demouiapp.screens.HomeScreen
import com.example.demouiapp.screens.ScreenA
import com.example.demouiapp.screens.ScreenB
import com.example.demouiapp.screens.ScreenC
import com.example.demouiapp.screens.ScreenD
import com.example.demouiapp.screens.ScreenE
import com.example.demouiapp.screensdetail.ScreenBDetail
import com.example.demouiapp.screensdetail.ScreenCDetail
import com.example.demouiapp.screensdetail.ScreenDDetail


//@Composable
//fun NavigationGraph(navController: NavHostController, studentViewModel: StudentViewModel) {
//    NavHost(navController = navController, startDestination = "home") {
//        composable("home") { HomeScreen(navController) }
//        composable("screen_a") {
//            // Khi màn hình ScreenA được mở, gọi hàm để tải 100 sinh viên
//            LaunchedEffect(Unit) {
//                studentViewModel.fetchFirst100Students()
//            }
//            ScreenA(navController, studentViewModel)
//        }
//        composable("screen_b") { ScreenB(navController) }  // Truyền navController vào
//        composable("screen_c") { ScreenC(navController) }  // Truyền navController vào
//        composable("screen_d") { ScreenD(navController) }  // Truyền navController vào
//        composable("screen_e") { ScreenE(navController) }  // Truyền navController vào
//
//        // Định nghĩa màn hình mới ScreenBExtra và nhận tham số môn học
//        composable("screen_b_extra/{subject}") { backStackEntry ->
//            val subject = backStackEntry.arguments?.getString("subject") ?: ""
//            ScreenBExtra(navController, studentViewModel, subject)
//        }
//
//        // Định nghĩa màn hình mới ScreenCExtra và nhận tham số môn học
//        composable("screen_c_extra/{city}") { backStackEntry ->
//            val city = backStackEntry.arguments?.getString("city") ?: ""
//            ScreenCExtra(navController, studentViewModel, city)
//        }
//    }
//}


@Composable
fun NavigationGraph(aidlDB: IMyMySchoolInterface?) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("screen_a") {
            ScreenA(navController, aidlDB)
        }
        composable("screen_b") {
            ScreenB(navController, aidlDB)
        }
        composable("screen_c") {
            ScreenC(navController, aidlDB)
        }
        composable("screen_d") {
            ScreenD(navController, aidlDB)
        }

        composable("screen_e") {
            ScreenE(navController, aidlDB)
        }

        composable("screen_b_detail/{subject}") { backStackEntry ->
            // Lấy tham số từ đường dẫn
            val selectedSubject = backStackEntry.arguments?.getString("subject") ?: "Math"
            ScreenBDetail(navController, aidlDB, selectedSubject)
        }

        composable("screen_c_detail/{city}") { backStackEntry ->
            // Lấy tham số từ đường dẫn
            val selectedCity = backStackEntry.arguments?.getString("city") ?: "Hanoi"
            ScreenCDetail(navController, aidlDB, selectedCity)
        }

        composable("screen_d_detail/{city}") { backStackEntry ->
            // Lấy tham số từ đường dẫn
            val selectedCity = backStackEntry.arguments?.getString("city") ?: "Hanoi"
            ScreenDDetail(navController, aidlDB, selectedCity)
        }

        composable("screen_e_detail/{inputFirstName}/{inputCity}") { backStackEntry ->
            // Lấy tham số từ đường dẫn
            val inputFirstName = backStackEntry.arguments?.getString("inputFirstName") ?: "Cuong"
            val inputCity = backStackEntry.arguments?.getString("inputCity") ?: "HCM"
//            ScreenEDetail(navController, aidlDB, inputFirstName,inputCity)
        }
    }
}