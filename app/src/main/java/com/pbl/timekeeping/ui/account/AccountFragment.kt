package com.pbl.timekeeping.ui.account

import androidx.navigation.fragment.findNavController
import com.pbl.timekeeping.R
import com.pbl.timekeeping.activities.MainActivity
import com.pbl.timekeeping.base.fragment.BaseFragment
import com.pbl.timekeeping.databinding.AccountClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment<AccountClass>(AccountClass::inflate){
    override fun initView() {

    }

    override fun initListeners() {
        dataBinding.layoutChangepassword.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_changePassFragment)
        }
        dataBinding.layoutLogout.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun initData() {
        val mainActivity = (requireActivity() as MainActivity)
        dataBinding.employee = mainActivity.employee
        dataBinding.executePendingBindings()
    }
}