package com.pbl.timekeeping.data.services

import com.pbl.timekeeping.base.network.BaseRemoteService
import com.pbl.timekeeping.base.network.NetworkResult
import com.pbl.timekeeping.data.apis.DepartmentApi
import com.pbl.timekeeping.data.modelJson.DepartmentJson
import com.pbl.timekeeping.data.models.Session
import javax.inject.Inject

class DepartmentService
@Inject constructor(private val departmentApi: DepartmentApi) : BaseRemoteService() {
    suspend fun getAllDepartments(): NetworkResult<List<DepartmentJson>> {
        return callApi { departmentApi.getAllDepartments() }
    }
    suspend fun getSessions(idDepartment : Int, page : Int) : NetworkResult<List<Session>>{
        return callApi { departmentApi.getSessions(idDepartment, page) }
    }
}