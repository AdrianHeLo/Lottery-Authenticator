package com.adrianhelo.lotteryauthenticator.ui.main

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.adrianhelo.lotteryauthenticator.data.local.AppDatabase
import com.adrianhelo.lotteryauthenticator.data.local.LotteryEntity
import com.adrianhelo.lotteryauthenticator.domain.generator.LotteryGenerator
import com.adrianhelo.lotteryauthenticator.domain.repository.LotteryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val lotteryRepository: LotteryRepository): ViewModel(), Observable {

    val lotteries = lotteryRepository.getLotteries

    @Bindable
    val lotteryName = MutableLiveData<String>()

    @Bindable
    val lotteryNumbers = MutableLiveData<String>()

    @Bindable
    val lotteryTimer = MutableLiveData<String>()

    private fun insertLottery(lottery: LotteryEntity) = viewModelScope.launch {
        lotteryRepository.insertLottery(lottery)
    }

    private fun updateLottery(lottery: LotteryEntity) = viewModelScope.launch {
        lotteryRepository.updateLottery(lottery)
    }

    private fun deleteLottery(lottery: LotteryEntity) = viewModelScope.launch {
        lotteryRepository.deleteLottery(lottery)
    }

    private fun deleteLotteries() = viewModelScope.launch {
        lotteryRepository.deleteLotteries()
    }

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
            lotteryRepository.insertLottery(lottery)
        }
    }

    fun generateNumbers(lottery: LotteryEntity){
        viewModelScope.launch {
            val numbers = LotteryGenerator.generate(lottery.numbersCounts, lottery.maxNumber).joinToString(" ")
            lotteryRepository.updateLottery(lottery.copy(currentsNumbers = numbers, lastGenerated = System.currentTimeMillis()))
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

}