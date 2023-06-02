package com.pbl.timekeeping.data.apis


import com.pbl.timekeeping.data.models.SessionRequest
import com.pbl.timekeeping.data.models.WorkSchedule
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WorkScheduleApi {
    @POST("work_schedule")
    suspend fun getWorkSchedule(
        @Body sessionRequest: SessionRequest
    ): Response<List<WorkSchedule>>
}