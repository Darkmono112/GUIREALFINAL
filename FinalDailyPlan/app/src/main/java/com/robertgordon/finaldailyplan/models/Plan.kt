package com.robertgordon.finaldailyplan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Plan(
    @PrimaryKey var time: Long,
    @ColumnInfo val plan: String
)