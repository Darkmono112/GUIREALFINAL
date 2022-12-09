package com.robertgordon.finaldailyplan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertgordon.finaldailyplan.databinding.FragmentHomeBinding
import com.robertgordon.finaldailyplan.GoalViewModel

class HomeViewModel: ViewModel() {

   //TODO figure if this is necessary
    val hasAGoal = MutableLiveData(false)

    fun hasAGoalHome():Boolean {
        return GoalViewModel().hasAGoal()
    }




}