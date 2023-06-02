package com.pbl.timekeeping.ui.changepassword

import android.text.TextUtils
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pbl.timekeeping.activities.MainActivity
import com.pbl.timekeeping.base.fragment.BaseFragment
import com.pbl.timekeeping.databinding.ChangePassClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePassFragment : BaseFragment<ChangePassClass>(ChangePassClass::inflate) {
    private val viewModel : ChangePassViewModel by viewModels()
    override fun initView() {

    }

    override fun initListeners() {
        dataBinding.editCurrentPass.doAfterTextChanged {
            viewModel.currentPass = it.toString()
        }
        dataBinding.editNewPass.doAfterTextChanged {
            viewModel.newPass = it.toString()
        }
        dataBinding.editConfirmPass.doAfterTextChanged {
            viewModel.confirmPass = it.toString()
        }
        dataBinding.btnSubmit.setOnClickListener {
            if(TextUtils.equals(viewModel.newPass,viewModel.confirmPass)){
                val mainActivity = (requireActivity() as MainActivity)
                val email = mainActivity.employee.email!!
                viewModel.changePassword(email).observe(viewLifecycleOwner){
                    if(it){
                        findNavController().popBackStack()
                    }
                    else{
                        Toast.makeText(requireContext(),"Thay đổi mật khẩu  không thành công, vui lòng kiểm tra lại mật khẩu",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(requireContext(),"Mật khẩu bạn nhập không khớp",
                Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initData() {

    }
}