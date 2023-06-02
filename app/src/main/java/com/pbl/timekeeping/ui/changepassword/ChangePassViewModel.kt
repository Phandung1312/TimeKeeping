package com.pbl.timekeeping.ui.changepassword

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.pbl.timekeeping.base.viewmodel.BaseViewModel
import com.pbl.timekeeping.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePassViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel() {
    var currentPass : String = ""
    var newPass : String = ""
    var confirmPass : String = ""

    fun changePassword(email : String) = liveData(handler){
        val result = accountRepository.changePassword(email, currentPass, newPass)
        emit(result)
    }
}