package com.pbl.timekeeping.data.models

data class SessionRequest(
    var id_employee : Int,
    var start_day : String,
    var end_day : String,
)