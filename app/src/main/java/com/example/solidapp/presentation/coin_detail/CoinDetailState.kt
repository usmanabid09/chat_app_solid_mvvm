package com.example.solidapp.presentation.coin_detail

import com.example.solidapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)