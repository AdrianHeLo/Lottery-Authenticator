package com.adrianhelo.lotteryauthenticator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LotteryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getLotteryDao(): LotteryDao

    companion object{
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    name = "lottery.db"
                ).build().also { INSTANCE = it }
            }
    }
}