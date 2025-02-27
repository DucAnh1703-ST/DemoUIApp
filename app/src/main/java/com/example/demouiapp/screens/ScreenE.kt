package com.example.demouiapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.demodatabase.IMyMySchoolInterface

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenE(navController: NavController, aidl: IMyMySchoolInterface?) {

    var userInput1 by remember { mutableStateOf("") } // Dữ liệu nhập vào từ người dùng
    var userInput2 by remember { mutableStateOf("") } // Dữ liệu nhập vào từ người dùng

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
                    .padding(top = 0.dp), // Để tránh bị che bởi TopAppBar
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Top 10 Student by City", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    // Nhập dữ liệu cho 2 TextField
                    TextField(
                        value = userInput1,
                        onValueChange = { userInput1 = it },
                        label = { Text("Enter Input 1") },
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    )

                    TextField(
                        value = userInput2,
                        onValueChange = { userInput2 = it },
                        label = { Text("Enter Input 2") },
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    )

                    // Dropdown list cho thành phố
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "City: ", fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().padding(16.dp))
                    }


                    // Nút Search để điều hướng
                    Button(
                        onClick = {
                            // Điều hướng tới màn hình mới "ScreenDDetail" và truyền dữ liệu vào
                            navController.navigate("screen_e_detail/$userInput1/$userInput2")
                        },
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    ) {
                        Text("Search")
                    }
                }
            }
        }
    )
}
