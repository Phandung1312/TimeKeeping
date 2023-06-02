package com.pbl.timekeeping.data.services

import com.pbl.timekeeping.base.network.BaseRemoteService
import com.pbl.timekeeping.base.network.BaseService
import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.apis.WorkScheduleApi
import com.pbl.timekeeping.data.models.EmployeeStatus
import com.pbl.timekeeping.data.models.SessionRequest
import com.pbl.timekeeping.data.models.WorkSchedule
import javax.inject.Inject

class WorkScheduleService @Inject constructor(private val workScheduleApi: WorkScheduleApi) : BaseRemoteService() {
    suspend fun getWorkSchedule( sessionRequest: SessionRequest) : NetworkResult<List<WorkSchedule>> {
        return callApi { workScheduleApi.getWorkSchedule(sessionRequest) }
    }
}