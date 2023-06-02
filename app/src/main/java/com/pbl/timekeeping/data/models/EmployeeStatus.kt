package com.pbl.timekeeping.data.models

import com.squareup.moshi.Json
import java.util.Date

data class EmployeeStatus(
    val id : String?,
    val name : String?,
    @Json(name = "img_avatar")
    val imgAvatar : String?,
    @Json(name = "roll_name")
    val roleName : String?,
    val status : Int?,
    val time : String?,
    val date : String?
)