package com.pbl.timekeeping.data.repositories

import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.models.SessionRequest
import com.pbl.timekeeping.data.services.EmployeeStatusService
import com.pbl.timekeeping.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeStatusRepository @Inject constructor(
    private val employeeStatusService: EmployeeStatusService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getHistoryAttendance( id : Int, startDay : String, endDay : String) = withContext(dispatcher){
        val sessionRequest = SessionRequest(id,startDay,endDay)
        when(val result = employeeStatusService.getEmployeeStatus(sessionRequest)){
            is NetworkResult.Success ->{
                result.data
            }
            is NetworkResult.Error ->{
                throw result.exception
            }
        }
    }
}