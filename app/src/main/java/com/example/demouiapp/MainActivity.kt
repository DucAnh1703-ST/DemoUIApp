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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.demodatabase.IMyMySchoolInterface
import com.example.demouiapp.navigation.NavigationGraph
import com.example.demouiapp.ui.theme.DemoUIAppTheme

class MainActivity : ComponentActivity() {

    private var aidlDB by mutableStateOf<IMyMySchoolInterface?>(null)
    private var isBound: Boolean = false // Biến kiểm tra kết nối dịch vụ

    private val serviceDBConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (service != null) {
                aidlDB = IMyMySchoolInterface.Stub.asInterface(service)
                isBound = true // Đánh dấu là đã kết nối dịch vụ thành công
                Log.d(
                    "MainActivity B",
                    "sumA: ${aidlDB?.getTop10StudentsBySumA("HCM")?.lastOrNull()}"
                )
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

