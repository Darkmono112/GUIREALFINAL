package com.robertgordon.finaldailyplan

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertgordon.finaldailyplan.models.Goal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
This Handles all of the buisness logic that will interact between the View and the Model
 */

class GoalViewModel:ViewModel() {

    val errorMessage = MutableLiveData("")

    val goals = ObservableArrayList<Goal>()

    init{
        loadGoals()
    }

    private fun loadGoals(){
        viewModelScope.launch {
            val loadedGoals = GoalsRepository.getAllGoals()
            goals.addAll(loadedGoals)
        }
    }


    fun toggleGoalCompletion(goal: Goal){ // once complete a goal should delete
        viewModelScope.launch {
            GoalsRepository.delGoal(goal)
            goals.remove(goal)
        }
    }

    fun saveAGoal(goalDes: String, goalDate: String){

        errorMessage.value = ""
        if(goalDes.isBlank() or goalDate.isBlank()){ // Don't except blank strings
            errorMessage.value = " Please enter A goal and date"
            viewModelScope.launch {
                delay(5000)
                errorMessage.value = ""
            }
            return
        }
        if( goalDate.length != 10 || !goalDate.contains("/")  ){

            errorMessage.value = "Proper date format is dd/mm/yyyy"
            println(goalDate.contains("/"))
            viewModelScope.launch {
                delay(10000)
                errorMessage.value = ""
            }
            return
        }
        if((goalDate.slice(0..1).toInt()) > 12 ){
            errorMessage.value = "Month Value Incorrect"
            viewModelScope.launch {
                delay(10000)
                errorMessage.value = ""
            }
            return
        }


        val goal = Goal(goalNum = 0,goalDes = goalDes, goalDate = goalDate, complete = false)
        viewModelScope.launch{
            GoalsRepository.createGoal(goal)
            goals.add(goal)
        }
    }

    public fun hasAGoal():Boolean{
        if(goals.size > 0){
            return true
        }
        errorMessage.value = " Must Have at least one goal to Plan Around"
        viewModelScope.launch {
            delay(10000)
            errorMessage.value = ""
        }

        return false
    }


}