//package com.example.demouiapp.screensdetail
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.demodatabase.IMyMySchoolInterface
//import com.example.demodatabase.Student
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScreenEDetail(navController: NavController, aidl: IMyMySchoolInterface?, inputFirstName: String, inputFirstName: String) {
//
//    var student by remember { mutableStateOf(Student) }
//
//    // Lọc sinh viên theo môn học khi ScreenBDetail được tạo
//    LaunchedEffect(Unit) {
//        student = aidl?.getStudentByPriority(inputFirstName,inputFirstName) ?: "emptyList()"
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Back") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            )
//        },
//        content = {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 56.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(top = 30.dp)
//                ) {
//                    Box(
//                        modifier = Modifier.fillMaxWidth(),
//                        contentAlignment = Alignment.Center
//                    ) {
////                        Text("Top Students for $city:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                    }
//
//                    LazyColumn(
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        itemsIndexed(students) { _, student ->
//                            // Card hiển thị First Name, Last Name, và Score
//                            Card(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(8.dp)
//                            ) {
//                                Column(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(16.dp)
//                                ) {
//                                    Text("Student ID: ${student.studentID}")
//                                    Text("First Name: ${student.firstName}")
//                                    Text("Last Name: ${student.lastName}")
//                                    Text("Date of Birth: ${student.dateOfBirth}")
//                                    Text("City: ${student.city}")
//                                    Text("Phone: ${student.phone}")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    )
//}