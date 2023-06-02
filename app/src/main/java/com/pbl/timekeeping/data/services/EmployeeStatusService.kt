package com.pbl.timekeeping.data.services

import com.pbl.timekeeping.base.network.BaseRemoteService
import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.apis.EmployeeStatusApi
import com.pbl.timekeeping.data.models.EmployeeStatus
import com.pbl.timekeeping.data.models.SessionRequest
import javax.inject.Inject

class EmployeeStatusService @Inject constructor(private val employeeStatusApi: EmployeeStatusApi) : BaseRemoteService() {
    suspend fun getEmployeeStatus( sessionRequest: SessionRequest) : NetworkResult<List<EmployeeStatus>>{
        return callApi { employeeStatusApi.getEmployeeStatus(sessionRequest) }
    }
}