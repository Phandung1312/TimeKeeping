package com.pbl.timekeeping.ui.workhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pbl.timekeeping.base.viewmodel.BaseViewModel
import com.pbl.timekeeping.data.models.EmployeeStatus
import com.pbl.timekeeping.data.repositories.EmployeeStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HistoryAttendanceViewModel @Inject constructor(
    private val employeeStatusRepository: EmployeeStatusRepository
) : BaseViewModel() {
    private var _attendancesList = MutableLiveData<List<EmployeeStatus>>()
    val attendancesList : LiveData<List<EmployeeStatus>> get() = _attendancesList

     fun getCurrentList(id : Int) {
        parentJob = viewModelScope.launch(handler) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, -5)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val startDay = dateFormat.format(calendar.time)
            val endDay = dateFormat.format(Date())
            val currentList = employeeStatusRepository.getHistoryAttendance(id, startDay, endDay)
            _attendancesList.postValue(currentList)
        }

        registerJobFinish()
    }
    fun searchHistory(id : Int,startDay : String, endDay : String){
        parentJob = viewModelScope.launch(handler) {
            val currentList = employeeStatusRepository.getHistoryAttendance(id, startDay, endDay)
            _attendancesList.postValue(currentList)
        }
        registerJobFinish()
    }
}