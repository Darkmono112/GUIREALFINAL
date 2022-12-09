package com.robertgordon.finaldailyplan

import androidx.room.Room
import com.robertgordon.finaldailyplan.models.Plan
import kotlin.time.measureTime

object PlansRepo {
    private val db: AppPlanDatabase

    private var cacheInitialized = false
    private var plansCache = mutableListOf<Plan>()

    init{
        db = Room.databaseBuilder(
            GoalsApplication.getInstance(),
            AppPlanDatabase::class.java,
            "plan-database"
        ).build()
    }


    suspend fun createPlan(plan:Plan){
        plan.time = db.getPlanDao().createPlan(plan)
        plansCache.add(plan)
    }

    suspend fun getAllPlans():List<Plan>{
        if(!cacheInitialized){
            plansCache.addAll(db.getPlanDao().getAllPlans())
            cacheInitialized = true
        }
        return plansCache
    }

    suspend fun update(plan: Plan){
        db.getPlanDao().updatePlan(plan)
        plansCache[plansCache.indexOfFirst {
            it.time == plan.time
        }]= plan
    }

    suspend fun delPlan(plan:Plan){
        db.getPlanDao().deletePlan(plan)
        plansCache.remove(plan)
    }


}