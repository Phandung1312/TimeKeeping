package com.pbl.timekeeping.ui.department

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pbl.timekeeping.base.fragment.BaseFragment
import com.pbl.timekeeping.common.ItemRvClickListener
import com.pbl.timekeeping.data.models.Department
import com.pbl.timekeeping.data.models.DepartmentModel
import com.pbl.timekeeping.databinding.DepartmentClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepartmentFragment : BaseFragment<DepartmentClass>(DepartmentClass::inflate),ItemRvClickListener {
    private val viewModel by viewModels<DepartmentViewModel>()
    private lateinit var lists : MutableList<Department>
    override fun initView() {

    }

    override fun initListeners() {

    }

    override fun initData() {
        viewModel.fetchData()
        registerAllExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverSmallLoadingEvent(viewModel, viewLifecycleOwner)
        viewModel.listDepartments.observe(viewLifecycleOwner){
                departments ->
            if(departments.isNotEmpty()){
                lists = ArrayList()
                lists.addAll(departments)
                val adapter = DepartmentAdapter(lists,
                this)
                dataBinding.rvDepartments.adapter = adapter
            }
        }
    }

    override fun onClick(item: Any?) {
        val department = (item as Department)
        findNavController().navigate(DepartmentFragmentDirections
            .actionDepartmentFragmentToDepartmentDetaislFragment(idDepartment = department.departmentId!!,
                departmentName = department.departmentName!!))
    }


}