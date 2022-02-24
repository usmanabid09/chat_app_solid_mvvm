package com.example.solidapp.data.repository

import com.example.solidapp.data.remote.CoinApi
import com.example.solidapp.data.remote.dto.CoinDetailDto
import com.example.solidapp.data.remote.dto.CoinDto
import com.example.solidapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}