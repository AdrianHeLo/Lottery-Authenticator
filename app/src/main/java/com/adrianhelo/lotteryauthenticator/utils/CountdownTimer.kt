package com.adrianhelo.lotteryauthenticator.utils

import android.os.CountDownTimer

class CountdownTimer ( private val intervalSeconds: Int, private val onTick: (Int)-> Unit, private val onFinishCallback: () -> Unit){

    private var timer: CountDownTimer? = null

    fun start() {
        timer?.cancel()
        timer = object : CountDownTimer(intervalSeconds * 1000L, 1000) {
            override fun onTick(ms: Long) {
                onTick((ms / 1000).toInt())
            }

            override fun onFinish() {
                onFinishCallback()
            }
        }.start()
    }

    fun stop() {
        timer?.cancel()
        timer = null
    }

}