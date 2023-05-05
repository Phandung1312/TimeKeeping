package com.pbl.timekeeping.data.models

import com.pbl.timekeeping.common.Utils

class Account(
    var id : Int?,
    var email : String?,
    var password : String?
){
    constructor() : this(null, null, null){

    }
    fun isValidateEmail(): Boolean = email?.let { Utils.validateEmail(it) } ?: false
    fun isValidatePassword() : Boolean{
        return (password?.isNotEmpty() == true)
    }
    fun validateData() : Boolean{
        return isValidateEmail() && isValidatePassword()
    }
}