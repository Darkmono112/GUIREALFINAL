package com.robertgordon.finaldailyplan

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robertgordon.finaldailyplan.models.Plan


@Database( entities = [Plan::class], version = 1)
abstract class AppPlanDatabase: RoomDatabase() {
    abstract fun getPlanDao(): PlanDoa
}