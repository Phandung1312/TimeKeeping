package com.pbl.timekeeping.data.models

data class ChangePasswordRequest(
    val email: String,
    val oldpassword: String,
    val newpassword: String,
    val token: String?
)
