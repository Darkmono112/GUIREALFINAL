package com.robertgordon.finaldailyplan

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertgordon.finaldailyplan.models.Plan
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlanViewModel:ViewModel() {

    val plans = ObservableArrayList<Plan>()
    var loadComplete = false
    var errorMessage = MutableLiveData("")

    init {
        loadPlans()
    }

    private fun loadPlans(){
        viewModelScope.launch{
            val loadedPlans = PlansRepo.getAllPlans()
            plans.addAll(loadedPlans)
        }
        loadComplete = true
    }


    fun updatePlans(planText: String, hour : Long){
        var toUpdate = plans[plans.indexOfFirst {
            it.time == hour
        }]
        if(planText.isNotBlank()){
            val newPlan = Plan(hour, planText)
            viewModelScope.launch {
                PlansRepo.update(newPlan)
                plans.remove(toUpdate)
                plans.add(newPlan)
            }
        }
    }

    fun createPlan(planText: String, hour: Long){
        if( planText.isBlank() || hour < 0 || hour > 24){
            errorMessage.value = " Enter a valid 24 Hr time and Plan"
            viewModelScope.launch {
                delay(5000)
                errorMessage.value =""
            }

            return

        }
        val plan = Plan(hour, planText)
        for(aPlan in plans){
            if( aPlan.time == plan.time){
                errorMessage.value = " Can not create dublicate plans, update on the plans Item"
                viewModelScope.launch {
                    delay(5000)
                    errorMessage.value =""
                }
                    return
        }
        }


        viewModelScope.launch {
            PlansRepo.createPlan(plan)
            plans.add(plan)
        }
    }

    fun clearPlans(){
        for( i in plans){
            viewModelScope.launch {
                PlansRepo.delPlan(i)
                plans.remove(i)
            }
        }
    }
}