package com.example.demouiapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenE(navController: NavController) {
    // Cấu hình giao diện cho màn hình E với TopAppBar chứa nút back
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Back to Home") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = {
            // Nội dung của màn hình
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp), // Để tránh bị che bởi TopAppBar
                contentAlignment = Alignment.Center
            ) {
                Text("This is Screen E")
            }
        }
    )
}