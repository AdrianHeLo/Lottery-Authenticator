package com.adrianhelo.lotteryauthenticator.utils

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CountdownTimer (private val intervalSeconds: Int){

    private val _time = MutableLiveData<Int>()
    val time: LiveData<Int> = _time
    private var timer: CountDownTimer? = null

    fun start() {
        timer?.cancel()
        startInternal()
    }

    private fun startInternal() {
        timer = object : CountDownTimer(
            intervalSeconds * 1000L,
            1000L
        ) {
            override fun onTick(ms: Long) {
                _time.value = (ms / 1000).toInt()
            }

            override fun onFinish() {
                _time.value = intervalSeconds
                startInternal() // 🔁 vuelve a empezar
            }
        }.start()
    }

    fun stop() {
        timer?.cancel()
        timer = null
    }

}