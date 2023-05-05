package com.pbl.timekeeping.data.repositories

import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.services.EmployeeService
import com.pbl.timekeeping.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeService: EmployeeService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getEmployeeById(idEmployee : Int) = withContext(dispatcher){
        when(val result = employeeService.getEmployeeById(idEmployee)){
            is NetworkResult.Success ->{
                result.data.toEmployee()
            }
            is NetworkResult.Error ->{
                throw result.exception
            }
        }
    }
}