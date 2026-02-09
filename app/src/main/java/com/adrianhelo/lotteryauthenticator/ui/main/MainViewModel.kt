package com.adrianhelo.lotteryauthenticator.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.adrianhelo.lotteryauthenticator.data.local.AppDatabase
import com.adrianhelo.lotteryauthenticator.data.local.LotteryEntity
import com.adrianhelo.lotteryauthenticator.domain.generator.LotteryGenerator
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val dao = AppDatabase.get(application).getLotteryDao()
    val lotteries = dao.getAll().asLiveData()

    fun generateNumbers(lottery: LotteryEntity){
        viewModelScope.launch {
            val numbers = LotteryGenerator.generate(lottery.numbersCounts, lottery.maxNumber).joinToString(" ")
            dao.update(lottery.copy(currentsNumbers = numbers, lastGenerated = System.currentTimeMillis()))
        }
    }

    /*
    fun addSampleData(){
        viewModelScope.launch {
            val lottery = LotteryEntity(
                name = "Lotería Nacional",
                maxNumber = 56,
                numbersCounts = 6,
                intervalSeconds = 30
            )
            dao.insert(lottery)
        }
    }
    */

    fun addLotteryAndGenerate() {
        viewModelScope.launch {

            val numbers = LotteryGenerator.generate(
                count = 6,
                max = 56).joinToString(" ")

            val lottery = LotteryEntity(
                name = "Lotería Nacional",
                maxNumber = 56,
                numbersCounts = 6,
                intervalSeconds = 30,
                currentsNumbers = numbers,
                lastGenerated = System.currentTimeMillis()
            )
            dao.insert(lottery)
        }
    }

}