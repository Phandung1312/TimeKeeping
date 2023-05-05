package com.pbl.timekeeping.data.modelJson

import com.pbl.timekeeping.data.models.Department
import com.squareup.moshi.Json

class DepartmentJson(
    @Json(name = "id_department")
    val departmentId : Int?,
    @Json(name = "name")
    val departmentName : String?,
    @Json(name = "decription")
    val description : String?,
    @Json(name = "num_users")
    val employeeQty : Int?
) {
    fun toDepartment() : Department{
        return Department(
            departmentId = departmentId,
            departmentName = departmentName,
            description = description,
            employeeQty = employeeQty
        )
    }
}