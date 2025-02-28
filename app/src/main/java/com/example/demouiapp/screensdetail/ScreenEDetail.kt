package com.example.demouiapp.screensdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun ScreenEDetail(
    navController: NavController,
    aidl: IMyMySchoolInterface?,
    inputFirstName: String,
    inputCity: String
) {

    var student by remember { mutableStateOf<Student?>(null) }

    LaunchedEffect(Unit) {
        // Lấy đối tượng Student duy nhất
        student = aidl?.getStudentByPriority(inputFirstName, inputCity)
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
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Có thể hiển thị thông tin về môn học hoặc tiêu đề ở đây
                        // Text("Top Student for $inputCity:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }

                    // Kiểm tra nếu student có dữ liệu, nếu không sẽ hiển thị thông báo
                    if (student != null) {
                        // Card hiển thị thông tin của Student duy nhất
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
                                Text("Information:", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Student ID: ${student?.studentID}")
                                Text("First Name: ${student?.firstName}")
                                Text("Last Name: ${student?.lastName}")
                                Text("Date of Birth: ${student?.dateOfBirth}")
                                Text("City: ${student?.city}")
                                Text("Phone: ${student?.phone}")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp)) // Thêm một khoảng cách giữa 2 Card

                        // Card hiển thị thông tin điểm của tất cả các môn học
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
                                Text(
                                    "Subjects and Scores:",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Lặp qua các môn học và hiển thị thông tin
                                student?.subjects?.forEach { subject ->
                                    Text("Subject: ${subject.name}, Score: ${subject.score}")
                                }
                            }
                        }
                    } else {
                        // Hiển thị khi không tìm thấy sinh viên
                        Text(
                            "No student found",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    )
}

