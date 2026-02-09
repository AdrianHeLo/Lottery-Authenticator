package com.adrianhelo.lotteryauthenticator.domain.generator

object LotteryGenerator {
    fun generate(count: Int, max: Int): List<String>{
        return (1..max)
            .shuffled()
            .take(count)
            .sorted().map { it.toString() }
    }
}