package com.robertgordon.finaldailyplan

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.robertgordon.finaldailyplan.models.Goal


@Dao
interface GoalDoa {
    @Query("SELECT * FROM goal")
    suspend fun getAllGoals(): List<Goal>

    @Insert
    suspend fun createGoal(goal: Goal) : Long

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)
}

