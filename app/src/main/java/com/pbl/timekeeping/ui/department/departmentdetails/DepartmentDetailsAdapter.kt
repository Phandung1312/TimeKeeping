package com.pbl.timekeeping.ui.department.departmentdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pbl.timekeeping.common.ItemRvClickListener
import com.pbl.timekeeping.data.models.Session
import com.pbl.timekeeping.databinding.ItemsWorkDayBinding

class DepartmentDetailsAdapter(
    private var sessionsList : MutableList<Session>,
    private var itemRvClickListener: ItemRvClickListener
) : RecyclerView.Adapter<DepartmentDetailsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : ItemsWorkDayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsWorkDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = sessionsList[holder.adapterPosition]
        with(holder){
            binding.session = session
            val childAdapter = AttendanceStatusAdapter(session.employeeStatusList.toMutableList(),
            itemRvClickListener)
            binding.rvEmployees.adapter = childAdapter
        }
        holder.binding.executePendingBindings()
    }
    override fun getItemCount(): Int = sessionsList.size
}