package com.example.solidapp.domain.repository

import com.example.solidapp.data.remote.dto.CoinDetailDto
import com.example.solidapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

}