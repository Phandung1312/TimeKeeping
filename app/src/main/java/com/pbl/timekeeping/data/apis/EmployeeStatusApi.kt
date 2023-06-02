package com.pbl.timekeeping.data.apis

import com.pbl.timekeeping.data.models.EmployeeStatus
import com.pbl.timekeeping.data.models.SessionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EmployeeStatusApi {
    @POST("session")
    suspend fun getEmployeeStatus(
        @Body sessionRequest: SessionRequest
    ): Response<List<EmployeeStatus>>
}