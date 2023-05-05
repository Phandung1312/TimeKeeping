package com.pbl.timekeeping.ui.department

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pbl.timekeeping.base.viewmodel.BaseViewModel
import com.pbl.timekeeping.data.models.Department
import com.pbl.timekeeping.data.repositories.DepartmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(private val departmentRepository: DepartmentRepository) :
    BaseViewModel() {
    private var _listDepartments = MutableLiveData<List<Department>>()
    val listDepartments: LiveData<List<Department>>
        get() = _listDepartments

    override fun fetchData() {
        showSmallLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val departments = departmentRepository.getAllDepartments()
            _listDepartments.postValue(departments)
        }
        registerJobFinish()
    }
}