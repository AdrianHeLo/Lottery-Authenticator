package com.adrianhelo.lotteryauthenticator.data.local

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lottery_table")
data class LotteryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "lottery_name")
    val name: String,
    @ColumnInfo(name = "lottery_max_number")
    val maxNumber: Int,
    @ColumnInfo(name = "lottery_number_counts")
    val numbersCounts: Int,
    @ColumnInfo(name = "lottery_interval_seconds")
    val intervalSeconds: Int,
    @ColumnInfo(name = "lottery_last_generated")
    val lastGenerated: Long = 0L,
    @ColumnInfo(name = "lottery_currents_numbers")
    val currentsNumbers: String = ""
)
