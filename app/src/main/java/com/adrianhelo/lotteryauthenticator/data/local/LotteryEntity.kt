package com.adrianhelo.lotteryauthenticator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lottery")
data class LotteryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val maxNumber: Int,
    val numbersCounts: Int,
    val intervalSeconds: Int,
    val lastGenerated: Long = 0L,
    val currentsNumbers: String = ""
)
