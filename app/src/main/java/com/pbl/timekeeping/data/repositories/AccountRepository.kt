package com.pbl.timekeeping.data.repositories

import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.services.AccountService
import com.pbl.timekeeping.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountService: AccountService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun login(email: String, password: String) = withContext(dispatcher) {
        when (val result = accountService.login(email, password)) {
            is NetworkResult.Success -> {
                result.data
            }
            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }

    suspend fun changePassword(email: String, oldPassword: String, newPassword: String) =
        withContext(dispatcher) {
            when (val result = accountService.changePassword(email, oldPassword, newPassword)) {
                is NetworkResult.Success ->{
                    result.data
                }
                is NetworkResult.Error ->{
                    throw result.exception
                }
            }
        }
    suspend fun resetPassword(email : String) = withContext(dispatcher){
        when (val result = accountService.resetPassword(email)){
            is NetworkResult.Success ->{
                result.data
            }
            is NetworkResult.Error ->{
                throw result.exception
            }
        }
    }
}