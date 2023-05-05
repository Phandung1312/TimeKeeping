package com.pbl.timekeeping.data.services

import com.pbl.timekeeping.base.network.BaseRemoteService
import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.apis.EmployeeStatusApi
import com.pbl.timekeeping.data.models.EmployeeStatus
import javax.inject.Inject

class EmployeeStatusService @Inject constructor(private val employeeStatusApi: EmployeeStatusApi) : BaseRemoteService() {
    suspend fun getEmployeeStatus( id : Int,
                                   startDay : String,
                                   endDay : String) : NetworkResult<List<EmployeeStatus>>{
        return callApi { employeeStatusApi.getEmployeeStatus(id, startDay, endDay) }
    }
}