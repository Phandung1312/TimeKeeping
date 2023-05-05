package com.pbl.timekeeping.data.repositories

import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.services.DepartmentService
import com.pbl.timekeeping.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DepartmentRepository @Inject constructor(
    private val departmentService: DepartmentService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAllDepartments() = withContext(dispatcher) {
        when (val result = departmentService.getAllDepartments()) {
            is NetworkResult.Success -> {
                result.data.map { it.toDepartment() }
            }
            is NetworkResult.Error -> {
                throw result.exception
            }
        }
    }
    suspend fun getSessions(idDepartment : Int, page : Int) = withContext(dispatcher){
        when (val result = departmentService.getSessions(idDepartment, page)){
            is NetworkResult.Success ->{
                result.data
            }
            is NetworkResult.Error ->{
                throw result.exception
            }
        }
    }

}