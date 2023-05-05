package com.pbl.timekeeping.ui.department.departmentdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pbl.timekeeping.base.viewmodel.BaseViewModel
import com.pbl.timekeeping.data.models.Session
import com.pbl.timekeeping.data.repositories.DepartmentRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DepartmentDetailsViewModel @Inject constructor(private val departmentRepository: DepartmentRepository) :
    BaseViewModel() {
        private var _listSession = MutableLiveData<List<Session>>()
        val listSession : LiveData<List<Session>>
        get() = _listSession
    fun getSession(idDepartment : Int, page : Int){
        showSmallLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val sessions = departmentRepository.getSessions(idDepartment, page)
            _listSession.postValue(sessions)
        }
        registerJobFinish()
    }
}