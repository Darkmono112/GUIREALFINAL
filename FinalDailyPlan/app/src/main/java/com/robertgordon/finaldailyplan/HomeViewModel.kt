package com.robertgordon.finaldailyplan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertgordon.finaldailyplan.databinding.FragmentHomeBinding
import com.robertgordon.finaldailyplan.GoalViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

   //TODO figure if this is necessary
    val hasAGoal = MutableLiveData("")

    fun hasAGoalHome():Boolean {
        if( GoalViewModel().hasAGoal()){
            return true
        }
        hasAGoal.value= "Must Set a Goal before making a plan "
        viewModelScope.launch {
            delay(10000)
            hasAGoal.value= ""
        }
        return false
    }




}