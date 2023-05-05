package com.pbl.timekeeping.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pbl.timekeeping.base.network.BaseNetworkException
import com.pbl.timekeeping.base.network.NetworkErrorException
import com.pbl.timekeeping.common.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {

    var baseNetworkException = MutableLiveData<Event<BaseNetworkException>>()
        protected set

    var networkException = MutableLiveData<Event<NetworkErrorException>>()
        protected set
    var isScreenLoading = MutableLiveData<Event<Boolean>>()
    var isSmallLoading = MutableLiveData<Event<Boolean>>()
        protected set

    var onNavigateToPage = MutableLiveData<Event<Int>>()
        protected set

    var errorMessageResourceId = MutableLiveData<Event<Int>>()
        protected set

    var notifyMessageResourceId = MutableLiveData<Event<Int>>()
        protected set

    var isLoadingMore = MutableLiveData<Event<Boolean>>()
        protected set

    var parentJob: Job? = null
        protected set

    protected fun registerJobFinish(){
        parentJob?.invokeOnCompletion {
            showSmallLoading(false)
            showScreenLoading(false)
        }
    }

    val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        parseErrorCallApi(exception)
    }

    protected fun showError(messageId: Int) {
        errorMessageResourceId.postValue(Event(messageId))
    }

    protected fun showNotify(messageId: Int) {
        notifyMessageResourceId.postValue(Event(messageId))
    }

    protected fun addBaseNetworkException(exception: BaseNetworkException) {
        baseNetworkException.postValue(Event(exception))
    }

    protected fun addNetworkException(exception: NetworkErrorException) {
        networkException.postValue(Event(exception))
    }

    protected fun showSmallLoading(isShow: Boolean) {
        isSmallLoading.postValue(Event(isShow))
    }
    protected fun showScreenLoading(isShow : Boolean){
        isScreenLoading.postValue(Event(isShow))
    }
    protected fun showLoadingMore(isShow: Boolean){
        isLoadingMore.postValue(Event(isShow))
    }

    protected fun navigateToPage(actionId: Int) {
        onNavigateToPage.postValue(Event(actionId))
    }

    protected open fun parseErrorCallApi(e: Throwable) {
        when (e) {
            is BaseNetworkException -> {
                baseNetworkException.postValue(Event(e))
            }
            is NetworkErrorException -> {
                networkException.postValue(Event(e))
            }
            else -> {
                val unknowException = BaseNetworkException()
                unknowException.mainMessage = e.message ?: "Something went wrong"
                baseNetworkException.postValue(Event(unknowException))
            }
        }
    }

    open fun fetchData() {

    }

}