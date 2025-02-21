package com.example.demouiapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize() // Lấp đầy toàn bộ không gian của màn hình
            .padding(16.dp), // Padding chung cho toàn bộ màn hình
        contentAlignment = Alignment.Center // Căn giữa nội dung
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center) // Căn giữa các nút trong Column
        ) {
            // Dòng Text ở trên cùng
            Text(
                text = "Home Screen",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(32.dp)) // Tạo khoảng cách giữa Text và các Button

            // Các Button, căn giữa chiều ngang và có độ dài bằng nhau
            Button(
                onClick = { navController.navigate("screen_a") },
                modifier = Modifier
                    .fillMaxWidth() // Chiếm toàn bộ chiều ngang
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Get ALL",
//                    modifier = Modifier.align(Alignment.Center) // Căn giữa nội dung bên trong button
                )
            }

            Button(
                onClick = { navController.navigate("screen_b") },
                modifier = Modifier
                    .fillMaxWidth() // Chiếm toàn bộ chiều ngang
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Top 10 by subject",
//                    modifier = Modifier.align(Alignment.Center) // Căn giữa nội dung bên trong button
                )
            }

            Button(
                onClick = { navController.navigate("screen_c") },
                modifier = Modifier
                    .fillMaxWidth() // Chiếm toàn bộ chiều ngang
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Top 10 by SumA",
//                    modifier = Modifier.align(Alignment.Center) // Căn giữa nội dung bên trong button
                )
            }

            Button(
                onClick = { navController.navigate("screen_d") },
                modifier = Modifier
                    .fillMaxWidth() // Chiếm toàn bộ chiều ngang
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Top 10 by SumB",
//                    modifier = Modifier.align(Alignment.Center) // Căn giữa nội dung bên trong button
                )
            }

            Button(
                onClick = { navController.navigate("screen_e") },
                modifier = Modifier
                    .fillMaxWidth() // Chiếm toàn bộ chiều ngang
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Search",
//                    modifier = Modifier.align(Alignment.Center) // Căn giữa nội dung bên trong button
                )
            }
        }
    }
}


