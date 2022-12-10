package com.robertgordon.finaldailyplan

import androidx.room.Room
import com.robertgordon.finaldailyplan.models.Goal

object GoalsRepository{
    private val db: AppGoalDatabase

    private var cacheInitialized = false
    private val goalsCache = mutableListOf<Goal>()


    init{
        db = Room.databaseBuilder(
            GoalsApplication.getInstance(),
            AppGoalDatabase::class.java,
            "goals-database"
        ).build()
    }

    suspend fun createGoal( goal: Goal){
        goal.goalNum = db.getGoalDoa().createGoal(goal)
        goalsCache.add(goal)

    }

    suspend fun getAllGoals():List<Goal>{
        if(!cacheInitialized){
            goalsCache.addAll(db.getGoalDoa().getAllGoals())
            cacheInitialized = true
        }
        return goalsCache
    }

    suspend fun update(goal: Goal){
        db.getGoalDoa().updateGoal(goal)
        goalsCache[goalsCache.indexOfFirst {
            it.goalNum == goal.goalNum
        }] = goal
    }


    suspend fun delGoal(goal: Goal){
        db.getGoalDoa().deleteGoal(goal)
        goalsCache.remove(goal)
    }




}