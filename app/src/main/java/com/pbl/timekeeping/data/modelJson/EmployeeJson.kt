package com.pbl.timekeeping.data.modelJson

import com.pbl.timekeeping.data.models.Employee
import com.squareup.moshi.Json

class EmployeeJson(
    @Json(name = "user")
    val employee : Employee? = null
){
    fun toEmployee() : Employee{
        return Employee(
            id = employee?.id,
            name = employee?.name,
            imgAvatar = employee?.imgAvatar,
            dob = employee?.dob,
            address = employee?.address,
            email = employee?.email,
            roleName = employee?.roleName
        )
    }
}