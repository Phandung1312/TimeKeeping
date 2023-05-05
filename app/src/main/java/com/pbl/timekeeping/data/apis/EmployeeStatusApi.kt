package com.pbl.timekeeping.data.apis

import com.pbl.timekeeping.data.models.EmployeeStatus
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeStatusApi {
    @GET("session")
    suspend fun getEmployeeStatus(
        id : Int,
        startDay : String,
        endDay : String
    ): Response<List<EmployeeStatus>>
}