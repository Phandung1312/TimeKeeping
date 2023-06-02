package com.pbl.timekeeping.ui.workschedule

import androidx.lifecycle.liveData
import com.pbl.timekeeping.base.viewmodel.BaseViewModel
import com.pbl.timekeeping.data.repositories.WorkScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkScheduleViewModel @Inject constructor(
    private val workScheduleRepository: WorkScheduleRepository
) : BaseViewModel() {

    fun getCurrentDay(id : Int,current : String) = liveData(handler){
        val result = workScheduleRepository.getWorkSchedule(id, current, current)
        emit(result.reversed())
    }
    fun getWorkDates(id : Int,startDay : String, endDay : String) = liveData(handler){
        val result = workScheduleRepository.getWorkSchedule(id, startDay, endDay)
        emit(result.reversed())
    }
}