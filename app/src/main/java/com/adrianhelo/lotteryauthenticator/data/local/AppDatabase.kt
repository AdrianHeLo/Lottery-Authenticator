package com.adrianhelo.lotteryauthenticator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LotteryEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getLotteryDao(): LotteryDao

    companion object{
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "lottery_db").build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}