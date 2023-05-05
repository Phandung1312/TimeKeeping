package com.pbl.timekeeping.ui.department.employeedetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pbl.timekeeping.base.fragment.BaseFragment
import com.pbl.timekeeping.databinding.EmployeeInfoClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeInfoFragment : BaseFragment<EmployeeInfoClass>(EmployeeInfoClass::inflate) {
    private val args : EmployeeInfoFragmentArgs by navArgs()
    private val viewModel by viewModels<EmployeeInfoViewModel>()
    override fun initView() {
    }

    override fun initListeners() {
        dataBinding.departmentName = args.departmentName
        dataBinding.toolbar.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    override fun initData() {
        val id = args.idEmployee
        viewModel.getEmployeeById(id)
        viewModel.employee.observe(viewLifecycleOwner){ employee ->
            dataBinding.employee = employee
        }
    }
}