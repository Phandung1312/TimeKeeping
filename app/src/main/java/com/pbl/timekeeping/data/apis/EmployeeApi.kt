package com.pbl.timekeeping.data.apis

import com.pbl.timekeeping.data.modelJson.EmployeeJson
import com.pbl.timekeeping.data.models.Employee
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface EmployeeApi {
    @GET("users/{id}")
    suspend fun getEmployeeById(
        @Path("id") id: Int
    ): Response<EmployeeJson>
}