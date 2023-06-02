package com.pbl.timekeeping.ui.workschedule


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pbl.timekeeping.data.models.WorkSchedule

import com.pbl.timekeeping.databinding.ItemsWorkScheduleBinding

class WorkScheduleAdapter(
    private var list : MutableList<WorkSchedule>
) : RecyclerView.Adapter<WorkScheduleAdapter.ViewHolder>(){
    inner class ViewHolder(val binding : ItemsWorkScheduleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsWorkScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workSchedule = list[holder.adapterPosition]
        holder.binding.workSchedule = workSchedule
        holder.binding.executePendingBindings()
    }


}