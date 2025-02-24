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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.demodatabase.IMyMySchoolInterface
import com.example.demouiapp.navigation.NavigationGraph
import com.example.demouiapp.ui.theme.DemoUIAppTheme
import com.example.demouiapp.viewmodel.StudentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private var aidl: IMyMySchoolInterface? = null
    private val studentViewModel: StudentViewModel by viewModels()

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (service != null) {
                aidl = IMyMySchoolInterface.Stub.asInterface(service)
                Log.d("MainActivity B", "onServiceConnected")
                // Truyền aidl cho ViewModel khi service được kết nối
                studentViewModel.setAidl(aidl)
            } else {
                Log.d("MainActivity B", "Service is null")
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            aidl = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sử dụng coroutine để bind service dưới nền
        CoroutineScope(Dispatchers.Main).launch {
            bindServiceInBackground()
        }

        enableEdgeToEdge()
        setContent {
            DemoUIAppTheme {
                val navController = rememberNavController()
                NavigationGraph(navController = navController, studentViewModel = studentViewModel)
            }
        }
    }

    private suspend fun bindServiceInBackground() {
        withContext(Dispatchers.IO) {
            val intent = Intent("com.example.demodatabase.IAidlDatabaseService")
            intent.setPackage("com.example.demodatabase")

            val isBound = bindService(intent, serviceConnection, BIND_AUTO_CREATE)
            if (isBound) {
                Log.d("MainActivity B", "bindService OK")
            } else {
                Log.d("MainActivity B", "bindService failed")
            }
        }
    }

    override fun onDestroy() {
        unbindService(serviceConnection)
        super.onDestroy()
    }
}


