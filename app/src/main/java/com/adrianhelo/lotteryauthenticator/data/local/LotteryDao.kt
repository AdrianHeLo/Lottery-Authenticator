package com.adrianhelo.lotteryauthenticator.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LotteryDao {

    @Query("SELECT * FROM lottery_table")
    fun getLotteries(): LiveData<List<LotteryEntity>>
    @Query("DELETE FROM lottery_table")
    suspend fun deleteLotteries()

    @Insert
    suspend fun insertLottery(lottery: LotteryEntity): Long
    @Update
    suspend fun updateLottery(lottery: LotteryEntity)
    @Delete
    suspend fun deleteLottery(lottery: LotteryEntity)
}