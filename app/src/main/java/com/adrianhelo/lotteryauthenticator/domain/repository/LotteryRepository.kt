package com.adrianhelo.lotteryauthenticator.domain.repository

import com.adrianhelo.lotteryauthenticator.data.local.LotteryDao
import com.adrianhelo.lotteryauthenticator.data.local.LotteryEntity

class LotteryRepository(private val lotteryDao: LotteryDao) {

    val getLotteries = lotteryDao.getLotteries()

    suspend fun deleteLotteries(){
        return lotteryDao.deleteLotteries()
    }

    suspend fun insertLottery(lottery: LotteryEntity): Long{
        return lotteryDao.insertLottery(lottery)
    }

    suspend fun updateLottery(lottery: LotteryEntity){
        return lotteryDao.updateLottery(lottery)
    }

    suspend fun deleteLottery(lottery: LotteryEntity){
        return lotteryDao.deleteLottery(lottery)
    }
}