package com.example.demouiapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demodatabase.IMyMySchoolInterface
import com.example.demodatabase.Student

class StudentViewModel: ViewModel() {
    private val _aidl = MutableLiveData<IMyMySchoolInterface?>()
    val aidl: LiveData<IMyMySchoolInterface?> get() = _aidl

    private val _first100Students = mutableStateOf<List<Student>>(emptyList())
    val first100Students: State<List<Student>> get() = _first100Students

    // Lưu aidl vào LiveData
    fun setAidl(aidlInstance: IMyMySchoolInterface?) {
        _aidl.value = aidlInstance
    }

    fun fetchFirst100Students() {
        val aidlInstance = _aidl.value
        if (_first100Students.value.isEmpty() && aidlInstance != null) {
            _first100Students.value = aidlInstance.first100Students ?: emptyList()
        }
    }

}