package com.example.demouiapp

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.example.demodatabase.IClientInterface
import com.example.demodatabase.IMyMySchoolInterface
import com.example.demodatabase.Student

class MyUIService : Service() {

    private var aidlDatabase: IMyMySchoolInterface? = null

    private val serviceDatabaseConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (service != null) {
                aidlDatabase = IMyMySchoolInterface.Stub.asInterface(service)
                Log.d("MyUIService", "onServiceConnected")
                // Truyền aidl cho ViewModel khi service được kết nối
            } else {
                Log.d("MyUIService", "Service is null")
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            aidlDatabase = null
        }
    }

    private val binder = object : IClientInterface.Stub() {
        override fun getFirst100Students(): List<Student> {
            return aidlDatabase?.first100Students?.toList() ?: listOf()
        }

        override fun getTop10StudentsBySubject(nameSubject: String?): List<Student> {
            return aidlDatabase?.getTop10StudentsBySubject(nameSubject ?: "")?.toList() ?: listOf()
        }

        override fun getTop10StudentsBySumA(nameCity: String?): List<Student> {
            return aidlDatabase?.getTop10StudentsBySumA(nameCity ?: "")?.toList() ?: listOf()
        }

        override fun getTop10StudentsBySumB(nameCity: String?): List<Student> {
            return aidlDatabase?.getTop10StudentsBySumB(nameCity ?: "")?.toList() ?: listOf()
        }

        override fun getStudentByPriority(firstName: String?, nameCity: String?): Student {
            return aidlDatabase?.getStudentByPriority(firstName ?: "", nameCity ?: "") ?: Student(
                studentID = -1,
                firstName = "",
                lastName = "",
                dateOfBirth = "",
                city = "",
                phone = "",
                subjects = listOf()
            )
        }
    }

    override fun onBind(intent: Intent): IBinder {
        bindDatabaseSevice()
        return binder
    }

    private fun bindDatabaseSevice() {
        val intent = Intent("com.example.demodatabase.IMyMySchoolInterface")
        intent.setPackage("com.example.demodatabase")
        bindService(intent, serviceDatabaseConnection, BIND_AUTO_CREATE)
    }
}