package com.robertgordon.finaldailyplan

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.robertgordon.finaldailyplan.models.Plan

@Dao
interface PlanDoa {
    @Query("SELECT * FROM `Plan`")
    suspend fun getAllPlans(): List<Plan>

    @Insert
    suspend fun createPlan(plan:Plan): Long

    @Update
    suspend fun updatePlan(plan: Plan)

    @Delete
    suspend fun deletePlan(plan: Plan)

}