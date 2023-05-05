package com.pbl.timekeeping.ui.department.employeedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.pbl.timekeeping.base.viewmodel.BaseViewModel
import com.pbl.timekeeping.data.models.Department
import com.pbl.timekeeping.data.models.Employee
import com.pbl.timekeeping.data.repositories.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeInfoViewModel @Inject constructor(private val employeeRepository: EmployeeRepository)
    : BaseViewModel() {
    private var _employee = MutableLiveData<Employee>()
    val employee: LiveData<Employee>
    get() = _employee
        fun getEmployeeById(idEmployee : Int) {
            parentJob = viewModelScope.launch(handler) {
                val departments = employeeRepository.getEmployeeById(idEmployee)
                _employee.postValue(departments)
            }
            registerJobFinish()
        }
}