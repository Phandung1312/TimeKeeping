package com.pbl.timekeeping.data.models

import com.squareup.moshi.Json
import java.util.Date

data class Session(
    @Json(name ="date")
    var date : String?,
    @Json(name = "employeeStatuses")
    var employeeStatusList : List<EmployeeStatus>
)