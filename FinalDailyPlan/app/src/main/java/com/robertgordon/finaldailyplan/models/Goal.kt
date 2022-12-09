package com.robertgordon.finaldailyplan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Goal(
    @PrimaryKey (autoGenerate = true) var goalNum: Long,
    @ColumnInfo(name = "posted_goal") val goalDes: String,
    @ColumnInfo(name = "goal_date") val goalDate: String,
    @ColumnInfo(name =  "completion_status") val complete: Boolean
)