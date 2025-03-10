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
fun ScreenC(navController: NavController, aidl: IMyMySchoolInterface?) {
    // Khai báo danh sách các city
    val cities = listOf(
        "Can Tho", "Da Lat", "Da Nang", "HCM", "Hai Phong",
        "Hanoi", "Hue", "Nha Trang", "Quang Ninh", "Vung Tau"
    )

    var selectedCity by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

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

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "City: ",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }

                    // Dropdown list
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                    ) {
                        TextField(
                            value = selectedCity,
                            onValueChange = {},
                            label = { Text("Select a city") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .menuAnchor(),
                            readOnly = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown Arrow"
                                )
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            cities.forEach { city ->
                                DropdownMenuItem(
                                    text = { Text(city) }, onClick = {
                                        selectedCity = city
                                        expanded = false
                                    })
                            }
                        }
                    }

                    // Thêm Button Search
                    Button(
                        onClick = {
                            /// Điều hướng tới màn hình mới "ScreenBExtra" và truyền city vào
                            navController.navigate("screen_c_detail/$selectedCity")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Search")
                    }
                }
            }
        }
    )
}