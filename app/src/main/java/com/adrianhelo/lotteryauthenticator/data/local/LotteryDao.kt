package com.adrianhelo.lotteryauthenticator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LotteryDao {

    @Query("SELECT * FROM lottery")
    fun getAll(): Flow<List<LotteryEntity>>

    @Insert
    suspend fun insert(lottery: LotteryEntity)

    @Update
    suspend fun update(lottery: LotteryEntity)

}