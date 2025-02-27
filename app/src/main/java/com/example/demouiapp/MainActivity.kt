package com.example.demouiapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.demodatabase.IMyMySchoolInterface
import com.example.demouiapp.navigation.NavigationGraph
import com.example.demouiapp.ui.theme.DemoUIAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//class MainActivity : ComponentActivity() {
//
//    private var aidl: IMyMySchoolInterface? = null
//    private val studentViewModel: StudentViewModel by viewModels()
//
//    private val serviceConnection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            if (service != null) {
//                aidl = IMyMySchoolInterface.Stub.asInterface(service)
//                Log.d("MainActivity B", "onServiceConnected")
//                // Truyền aidl cho ViewModel khi service được kết nối
//                studentViewModel.setAidl(aidl)
//
//            } else {
//                Log.d("MainActivity B", "Service is null")
//            }
//        }
//
//        override fun onServiceDisconnected(name: ComponentName?) {
//            aidl = null
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Sử dụng coroutine để bind service dưới nền
//        CoroutineScope(Dispatchers.Main).launch {
//            bindServiceInBackground()
//        }
//
//        enableEdgeToEdge()
//        setContent {
//            DemoUIAppTheme {
//                val navController = rememberNavController()
//                NavigationGraph(navController = navController, studentViewModel = studentViewModel)
//            }
//        }
//    }
//
//    private suspend fun bindServiceInBackground() {
//        withContext(Dispatchers.IO) {
//            val intent = Intent("com.example.demodatabase.IMyMySchoolInterface")
//            intent.setPackage("com.example.demodatabase")
//
//            val isBound = bindService(intent, serviceConnection, BIND_AUTO_CREATE)
//            if (isBound) {
//                Log.d("MainActivity B", "bindService OK")
//            } else {
//                Log.d("MainActivity B", "bindService failed")
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        unbindService(serviceConnection)
//        super.onDestroy()
//    }
//}

class MainActivity : ComponentActivity() {

    private var aidlDB by mutableStateOf<IMyMySchoolInterface?>(null)
    private var isBound: Boolean = false // Biến kiểm tra kết nối dịch vụ

    private val serviceDBConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (service != null) {
                aidlDB = IMyMySchoolInterface.Stub.asInterface(service)
                isBound = true // Đánh dấu là đã kết nối dịch vụ thành công
                Log.d("MainActivity B", "sumA: ${aidlDB?.getTop10StudentsBySumA("HCM")?.lastOrNull()}")
            } else {
                Log.d("MainActivity B", "Service is null")
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            aidlDB = null
            isBound = false // Đánh dấu là dịch vụ đã bị ngắt kết nối
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        bindDatabaseServiceAIDL()

        setContent {
            DemoUIAppTheme {
                NavigationGraph(aidlDB)
            }
        }
    }


    private fun bindDatabaseServiceAIDL() {
        val intent = Intent("com.example.demodatabase.IMyMySchoolInterface")
        intent.setPackage("com.example.demodatabase")
        bindService(intent, serviceDBConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        if (isBound) {
            unbindService(serviceDBConnection)
        }
        super.onDestroy()
    }
}

