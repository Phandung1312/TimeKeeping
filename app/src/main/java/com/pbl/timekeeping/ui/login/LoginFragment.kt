package com.pbl.timekeeping.ui.login

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pbl.timekeeping.R
import com.pbl.timekeeping.activities.MainActivity
import com.pbl.timekeeping.base.dialogs.LoadingDialog
import com.pbl.timekeeping.base.fragment.BaseFragment
import com.pbl.timekeeping.databinding.LoginClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginClass>(LoginClass::inflate) {
    private val viewModel by viewModels<LoginViewModel>()
    override fun initView() {
        (requireActivity() as MainActivity).binding.bottomNavigation.visibility = View.GONE
        dataBinding.viewModel = viewModel
    }

    override fun initListeners() {
        dataBinding.btnLogin.setOnClickListener {
            viewModel.login()
            viewModel.employee.observe(viewLifecycleOwner){ employee ->
                if(employee != null){
                    val mainActivity = (requireActivity() as MainActivity)
                    mainActivity.binding.bottomNavigation.visibility = View.VISIBLE
                    mainActivity.employee = employee
                    findNavController().navigate(R.id.departmentFragment)
                }
                else{
                    Toast.makeText(requireContext(),"Tài khoản hoặc mật khẩu không chính xác",
                    Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun initData() {
        registerAllExceptionEvent(viewModel, viewLifecycleOwner)
    }
}