package com.pbl.timekeeping.data.apis

import com.pbl.timekeeping.data.modelJson.DepartmentJson
import com.pbl.timekeeping.data.models.Session
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DepartmentApi {
    @GET("departments")
    suspend fun getAllDepartments() : Response<List<DepartmentJson>>

    @POST("work_schedule/getby")
    @FormUrlEncoded
    suspend fun getSessions(
        @Field("id_department") idDepartment : Int,
        @Field("page") page : Int
    ): Response<List<Session>>
}