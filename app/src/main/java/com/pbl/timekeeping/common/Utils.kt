package com.pbl.timekeeping.common

object Utils {
    const val BASE_URL : String = "http://192.168.1.2:6868/api/"
    fun validateEmail(email : String) : Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isEmptyText(text : String?) : Boolean{
        return (text?.isEmpty() == true)
    }
}