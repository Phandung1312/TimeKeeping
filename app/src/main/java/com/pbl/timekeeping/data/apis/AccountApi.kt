package com.pbl.timekeeping.data.apis

import com.pbl.timekeeping.data.models.Account
import com.pbl.timekeeping.data.models.ChangePasswordRequest
import com.pbl.timekeeping.data.models.Employee
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccountApi {
    @POST("users/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email : String,
        @Field("password") password : String,
    ) : Response<Employee>

    @POST("resetpassword")
    @FormUrlEncoded
    suspend fun resetPassword(
      @Field("email") email : String
    ) : Response<Boolean>

    @POST("users/changepassword")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest
    ): Response<Boolean>
}