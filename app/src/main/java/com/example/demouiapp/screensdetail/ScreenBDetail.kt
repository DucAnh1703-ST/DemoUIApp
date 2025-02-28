package com.example.demouiapp.screensdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.demodatabase.IMyMySchoolInterface
import com.example.demodatabase.Student

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBDetail(navController: NavController, aidl: IMyMySchoolInterface?, subject: String) {

    var students by remember { mutableStateOf(emptyList<Student>()) }

    // Lọc sinh viên theo môn học khi ScreenBDetail được tạo
    LaunchedEffect(Unit) {
        students = aidl?.getTop10StudentsBySubject(subject) ?: emptyList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Back") },
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
                    .padding(top = 56.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp)
                ) {
                    // Chỉ hiển thị tiêu đề nếu có sinh viên trong danh sách
                    if (students.isNotEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Top Students for $subject:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Kiểm tra nếu có sinh viên, nếu không hiển thị thông báo
                    if (students.isEmpty()) {
                        Text(
                            "No students found for subject: $subject",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally) // Căn giữa
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            itemsIndexed(students) { _, student ->
                                // Card hiển thị First Name, Last Name, và Score
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        Text("First Name: ${student.firstName}")
                                        Text("Last Name: ${student.lastName}")
                                        // Lọc điểm của môn học đã chọn từ danh sách môn học
                                        val score =
                                            student.subjects.find { it.name == subject }?.score
                                        if (score != null) {
                                            Text("Score: $score")
                                        } else {
                                            Text("Score: Not Available")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}


