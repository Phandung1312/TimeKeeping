package com.pbl.timekeeping.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pbl.timekeeping.base.viewmodel.BaseViewModel
import com.pbl.timekeeping.data.models.Account
import com.pbl.timekeeping.data.models.Employee
import com.pbl.timekeeping.data.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val accountRepository: AccountRepository) : BaseViewModel() {
    var currentAccount : MutableLiveData<Account> = MutableLiveData(Account())
    var isChanged : MutableLiveData<Boolean> = MutableLiveData(false)
    private var _employee = MutableLiveData<Employee>()
    val employee : LiveData<Employee> get() = _employee

    fun login(){
        showScreenLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val check = accountRepository.login(currentAccount.value!!.email!!,
                currentAccount.value!!.password!!)
            _employee.postValue(check)
        }
        registerJobFinish()
    }
    fun onEmailChanged(text: CharSequence?,
                       start: Int,
                       before: Int,
                       count: Int){
        isChanged.postValue(true)
        val newEmail = text?.toString()
        val account = currentAccount.value
        if(newEmail != account?.email){
            account?.email = newEmail
            currentAccount.postValue(account!!)
        }
    }
    fun onPasswordChanged(text: CharSequence?,
                       start: Int,
                       before: Int,
                       count: Int){
        isChanged.postValue(true)
        val newPassword = text?.toString()
        val account = currentAccount.value
        if(newPassword != account?.email){
            account?.password = newPassword
            currentAccount.postValue(account!!)
        }
    }

}