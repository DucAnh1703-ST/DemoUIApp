package com.example.demouiapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.demodatabase.Student
import com.example.demouiapp.viewmodel.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenA(navController: NavController, studentViewModel: StudentViewModel) {
    val first100Students = studentViewModel.first100Students.value

    var openDialog by remember { mutableStateOf(false) }
    var selectedStudent by remember { mutableStateOf<Student?>(null) }

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp), // Để tránh bị che bởi TopAppBar
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp) // Đảm bảo có khoảng cách trên
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("First 100 Students:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(first100Students) { index, student ->
                            // Card hiển thị thông tin của mỗi sinh viên
                            // Card cho mỗi sinh viên
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable {
                                        selectedStudent = student
                                        openDialog = true // Mở dialog khi click vào sinh viên
                                    }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text("Student ID: ${student.studentID}")
                                    Text("First Name: ${student.firstName}")
                                    Text("Last Name: ${student.lastName}")
                                    Text("Date of Birth: ${student.dateOfBirth}")
                                    Text("City: ${student.city}")
                                    Text("Phone: ${student.phone}")
                                }
                            }
                        }
                    }
                }
            }
        }
    )

    // Hiển thị Dialog khi click vào sinh viên
    if (openDialog && selectedStudent != null) {
        AlertDialog(
            onDismissRequest = { openDialog = false },
            title = { Text("Subjects and Scores") },
            text = {
                selectedStudent?.subjects?.let { subjects ->
                    Column {
                        subjects.forEach { subject ->
                            Text("${subject.name}: ${subject.score}")
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { openDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}

