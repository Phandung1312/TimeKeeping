package com.pbl.timekeeping.ui.workschedule

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pbl.timekeeping.activities.MainActivity
import com.pbl.timekeeping.base.fragment.BaseFragment
import com.pbl.timekeeping.data.models.EmployeeStatus
import com.pbl.timekeeping.data.models.WorkSchedule
import com.pbl.timekeeping.databinding.WorkScheduleClass
import com.pbl.timekeeping.ui.department.departmentdetails.AttendanceStatusAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class WorkScheduleFragment : BaseFragment<WorkScheduleClass>(WorkScheduleClass::inflate) {
    private val viewModel : WorkScheduleViewModel by viewModels()
    private lateinit var list : MutableList<WorkSchedule>
    private lateinit var adapter : WorkScheduleAdapter
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
            viewModel.getWorkDates(id!!,startDay,endDay).observe(viewLifecycleOwner){
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun initData() {
        list = ArrayList()
        adapter = WorkScheduleAdapter(list)
        dataBinding.rvWorkSchedules.adapter = adapter
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val startDay = dateFormat.format(Date())
        calendar.add(Calendar.DATE, +5)
        val endDay = dateFormat.format(Date())
        viewModel.getWorkDates(id!!,startDay,endDay).observe(viewLifecycleOwner){
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }
}