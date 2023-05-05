package com.pbl.timekeeping.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.pbl.timekeeping.R
import com.pbl.timekeeping.activities.MainActivity
import com.pbl.timekeeping.base.activities.BaseActivity
import com.pbl.timekeeping.base.network.BaseNetworkException
import com.pbl.timekeeping.base.viewmodel.BaseViewModel
import com.pbl.timekeeping.common.EventObserver
import com.pbl.timekeeping.common.ItemRvClickListener
import com.pbl.timekeeping.data.models.Account
import com.pbl.timekeeping.data.models.Department
import com.pbl.timekeeping.data.models.Employee

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
abstract class BaseFragment<VB : ViewDataBinding>(private val inflate : Inflate<VB>) : Fragment() {
    private var _dataBinding: VB? = null
    val dataBinding get() = _dataBinding!!
    lateinit var employee: Employee
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _dataBinding = inflate.invoke(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        initView()
        initListeners()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.employee = (requireActivity() as MainActivity).employee
    }
    abstract fun initView()
    abstract fun initListeners()
    abstract fun initData()

    protected fun navigateToPage(actionId: Int){
        findNavController().navigate(actionId)
    }

    protected fun showSmallLoading(isShow: Boolean) {
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showSmallLoading(isShow)
        }
    }
    protected fun showScreenLoading(isShow: Boolean){
        val activity = requireActivity()
        if(activity is BaseActivity){
            activity.showScreenLoading(isShow)
        }
    }
    protected fun showErrorMessage(e: BaseNetworkException) {
        showErrorMessage(e.mainMessage)
    }

    protected fun showErrorMessage(messageId: Int){
        val message = requireContext().getString(messageId)
        showErrorMessage(message)
    }

    protected fun showErrorMessage(message: String){
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showErrorDialog(message)
        }
    }

    protected fun showNotify(title: String?, message: String) {
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showNotifyDialog(title ?: getDefaultNotifyTitle(), message)
        }
    }

    protected fun showNotify(titleId: Int = R.string.default_notify_title, messageId: Int) {
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showNotifyDialog(titleId, messageId)
        }
    }

    protected fun registerObserverExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.baseNetworkException.observe(viewLifecycleOwner, EventObserver {
            showErrorMessage(it)
        })
    }

    protected fun registerObserverNetworkExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.networkException.observe(viewLifecycleOwner, EventObserver {
            showNotify(getDefaultNotifyTitle(), it.message ?: "Network error")
        })
    }

    protected fun registerObserverMessageEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.errorMessageResourceId.observe(viewLifecycleOwner, EventObserver { message ->
            showErrorMessage(message)
        })
    }

    protected fun registerObserverLoadingMoreEvent(viewModel: BaseViewModel,
                                                   viewLifecycleOwner: LifecycleOwner
    ){
        viewModel.isLoadingMore.observe(viewLifecycleOwner,EventObserver{
                isShow->
            showLoadingMore(isShow)
        })
    }

    protected fun showLoadingMore(isShow: Boolean){

    }


    private fun getDefaultNotifyTitle(): String {
        return getString(R.string.default_notify_title)
    }

    protected fun registerAllExceptionEvent( viewModel: BaseViewModel,
                                             viewLifecycleOwner: LifecycleOwner
    ){
        registerObserverExceptionEvent(viewModel,viewLifecycleOwner)
        registerObserverNetworkExceptionEvent(viewModel,viewLifecycleOwner)
        registerObserverMessageEvent(viewModel,viewLifecycleOwner)
    }

    protected fun registerObserverSmallLoadingEvent(viewModel: BaseViewModel, viewLifecycleOwner: LifecycleOwner){
        viewModel.isSmallLoading.observe(viewLifecycleOwner,EventObserver{
                isShow ->
                showSmallLoading(isShow)
        })
    }
    protected fun registerObserverScreenLoading(viewModel: BaseViewModel, viewLifecycleOwner: LifecycleOwner){
        viewModel.isScreenLoading.observe(viewLifecycleOwner, EventObserver{
            isShow ->
            showScreenLoading(isShow)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _dataBinding = null
    }
}