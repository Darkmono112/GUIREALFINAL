package com.robertgordon.finaldailyplan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Plan(
    @PrimaryKey val time: Int,
    @ColumnInfo val plan: String,
)