package com.pbl.timekeeping.data.services

import com.pbl.timekeeping.base.network.BaseRemoteService
import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.apis.AccountApi
import com.pbl.timekeeping.data.models.Account
import com.pbl.timekeeping.data.models.Employee
import javax.inject.Inject

class AccountService @Inject constructor(private val accountApi: AccountApi) : BaseRemoteService() {
    suspend fun login(email: String, password: String): NetworkResult<Employee> {
        return callApi {
            accountApi.login(email, password)
        }
    }

    suspend fun resetPassword(email: String): NetworkResult<Boolean> {
        return callApi { accountApi.resetPassword(email) }
    }

    suspend fun changePassword(
        email: String,
        oldPassword: String,
        newPassword: String
    ): NetworkResult<Boolean> {
        return callApi { accountApi.changePassword(email, oldPassword, newPassword) }
    }
}