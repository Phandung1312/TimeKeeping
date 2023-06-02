package com.pbl.timekeeping.ui.workhistory

import android.app.DatePickerDialog
import androidx.fragment.app.viewModels
import com.pbl.timekeeping.activities.MainActivity
import com.pbl.timekeeping.base.fragment.BaseFragment
import com.pbl.timekeeping.common.ItemRvClickListener
import com.pbl.timekeeping.data.models.EmployeeStatus
import com.pbl.timekeeping.databinding.HistoryAttendanceClass
import com.pbl.timekeeping.ui.department.departmentdetails.AttendanceStatusAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class HistoryAttendanceFragment : BaseFragment<HistoryAttendanceClass>(HistoryAttendanceClass::inflate),ItemRvClickListener {
    private val viewModel : HistoryAttendanceViewModel by viewModels()
    private lateinit var list : MutableList<EmployeeStatus>
    private lateinit var adapter : AttendanceStatusAdapter
    private var id : Int? = 0
    override fun initView() {
        val mainActivity = (requireActivity() as MainActivity)
        this.id = mainActivity.employee.id
        val calendar = Calendar.getInstance()
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)
        dataBinding.tvStartDay.text = "$year-${month}-$dayOfMonth"
        dataBinding.tvEndDay.text = "$year-${month}-$dayOfMonth"
    }

    override fun initListeners() {
        dataBinding.tvStartDay.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(requireContext(),
                { view, year, month, dayOfMonth ->
                    dataBinding.tvStartDay.text = "$year-${month + 1}-$dayOfMonth"
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE))
            dialog.show()
        }
        dataBinding.tvEndDay.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(requireContext(),
                { view, year, month, dayOfMonth ->
                    dataBinding.tvEndDay.text = "$year-${month + 1}-$dayOfMonth"
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE))
            dialog.show()
        }
        dataBinding.ivSearch.setOnClickListener {
            val startDay = dataBinding.tvStartDay.text.toString()
            val endDay = dataBinding.tvEndDay.text.toString()
            viewModel.searchHistory(id!!,startDay,endDay)
        }
    }

    override fun initData() {
        list = ArrayList()
        adapter = AttendanceStatusAdapter(
            list,this
        )
        dataBinding.rvHistoryAttendances.adapter = adapter
        viewModel.getCurrentList(id!!)
        viewModel.attendancesList.observe(viewLifecycleOwner){
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onClick(item: Any?) {

    }
}