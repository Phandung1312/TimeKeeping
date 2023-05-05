package com.pbl.timekeeping.ui.department.departmentdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pbl.timekeeping.common.ItemRvClickListener
import com.pbl.timekeeping.data.models.EmployeeStatus
import com.pbl.timekeeping.databinding.ItemsAttendanceStatusBinding
import com.pbl.timekeeping.databinding.ItemsWorkDayBinding

class AttendanceStatusAdapter(
    private var employeeStatus: MutableList<EmployeeStatus>,
    private val itemRvClickListener: ItemRvClickListener
) : RecyclerView.Adapter<AttendanceStatusAdapter.ViewHolder>(){
    inner class ViewHolder(val binding : ItemsAttendanceStatusBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsAttendanceStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employeeStatus = employeeStatus[holder.adapterPosition]
        with(holder){
            binding.employeeStatus = employeeStatus
            binding.layoutMain.setOnClickListener {
                itemRvClickListener.onClick(employeeStatus)
            }
        }
        holder.binding.executePendingBindings()
    }
    override fun getItemCount(): Int = employeeStatus.size
}