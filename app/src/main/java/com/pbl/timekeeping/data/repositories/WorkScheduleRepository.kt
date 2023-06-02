package com.pbl.timekeeping.data.repositories

import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.models.SessionRequest
import com.pbl.timekeeping.data.services.WorkScheduleService
import com.pbl.timekeeping.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkScheduleRepository @Inject constructor(
    private val workScheduleService: WorkScheduleService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getWorkSchedule( id : Int, startDay : String, endDay : String) = withContext(dispatcher){
        val sessionRequest = SessionRequest(id,startDay,endDay)
        when(val result = workScheduleService.getWorkSchedule(sessionRequest)){
            is NetworkResult.Success ->{
                result.data
            }
            is NetworkResult.Error ->{
                throw result.exception
            }
        }
    }
}