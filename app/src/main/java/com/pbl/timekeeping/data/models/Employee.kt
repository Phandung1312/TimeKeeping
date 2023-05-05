package com.pbl.timekeeping.data.models

import com.squareup.moshi.Json

data class Employee(
    @Json(name = "id_user")
    val id : Int? = 0,
    val name : String? = null,
    @Json(name = "img_avatar")
    val imgAvatar : String? = null,
    @Json(name = "date_of_birth")
    val dob : String? = null,
    val address : String? = null,
    val email : String?= null,
    @Json(name = "rolename")
    val roleName : String?= null
)