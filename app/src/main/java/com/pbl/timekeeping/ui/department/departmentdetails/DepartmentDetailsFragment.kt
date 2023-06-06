package com.pbl.timekeeping.ui.department.departmentdetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pbl.timekeeping.base.fragment.BaseFragment
import com.pbl.timekeeping.common.ItemRvClickListener
import com.pbl.timekeeping.data.models.Employee
import com.pbl.timekeeping.data.models.EmployeeStatus
import com.pbl.timekeeping.data.models.Session
import com.pbl.timekeeping.databinding.DepartmentDetailsClass
import com.pbl.timekeeping.ui.department.DepartmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepartmentDetailsFragment : BaseFragment<DepartmentDetailsClass>(DepartmentDetailsClass::inflate),ItemRvClickListener {
    private val args : DepartmentDetailsFragmentArgs by navArgs()
    private lateinit var lists : MutableList<Session>
    private lateinit var adapter : DepartmentDetailsAdapter
    private val viewModel by viewModels<DepartmentDetailsViewModel>()
    override fun initView() {
        dataBinding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        dataBinding.toolbar.title = args.departmentName
        lists = ArrayList()
        adapter = DepartmentDetailsAdapter(
            lists,
            this
        )
        dataBinding.rvMain.adapter = adapter
    }

    override fun initListeners() {
        dataBinding.swipeRefreshLayout.setOnRefreshListener {
            initData()
            dataBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun initData() {
        val id = args.idDepartment
        viewModel.getSession(id, page = 1)
      //  registerAllExceptionEvent(viewModel, viewLifecycleOwner)
        viewModel.listSession.observe(viewLifecycleOwner){
            lists.clear()
            lists.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onClick(item: Any?) {
        val idEmployee = (item as EmployeeStatus).id?.toInt()
        findNavController().navigate(DepartmentDetailsFragmentDirections
            .actionDepartmentDetaislFragmentToEmployeeInfoFragment(args.departmentName,
            idEmployee!!))
    }
}