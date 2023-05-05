package com.pbl.timekeeping.ui.department

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pbl.timekeeping.common.ItemRvClickListener
import com.pbl.timekeeping.data.models.Department
import com.pbl.timekeeping.databinding.ItemsDepartmentBinding

class DepartmentAdapter(
    private var departments : MutableList<Department>,
    private val itemRvClickListener: ItemRvClickListener
) : RecyclerView.Adapter<DepartmentAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : ItemsDepartmentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsDepartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val department = departments[holder.adapterPosition]
        holder.binding.item  = department
        holder.binding.layoutMain.setOnClickListener {
            itemRvClickListener.onClick(department)
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = departments.size

}