package com.robertgordon.finaldailyplan

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robertgordon.finaldailyplan.models.Goal

@Database(entities = [Goal::class], version = 1)
abstract class AppGoalDatabase: RoomDatabase() {
    abstract fun getGoalDoa():GoalDoa

}