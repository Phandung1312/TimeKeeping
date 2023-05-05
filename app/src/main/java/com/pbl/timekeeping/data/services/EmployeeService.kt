package com.pbl.timekeeping.data.services

import com.pbl.timekeeping.base.network.BaseRemoteService
import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.apis.EmployeeApi
import com.pbl.timekeeping.data.modelJson.EmployeeJson
import com.pbl.timekeeping.data.models.Employee
import javax.inject.Inject

class EmployeeService @Inject constructor(private val employeeApi: EmployeeApi)
    : BaseRemoteService() {
        suspend fun getEmployeeById(id : Int) : NetworkResult<EmployeeJson> {
            return callApi { employeeApi.getEmployeeById(id) }
        }
}