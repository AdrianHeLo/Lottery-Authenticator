package com.adrianhelo.lotteryauthenticator.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrianhelo.lotteryauthenticator.domain.repository.LotteryRepository
import com.adrianhelo.lotteryauthenticator.ui.main.MainViewModel

class LotteryFactory(private val lotteryRepository: LotteryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(lotteryRepository) as T
        }
        throw IllegalArgumentException("Unknow View Model Class")
    }
}