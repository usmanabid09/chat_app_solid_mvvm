package com.example.solidapp.presentation.coin_detail

import androidx.lifecycle.*
import com.example.solidapp.common.Constants.PARAM_COIN_ID
import com.example.solidapp.common.Resource
import com.example.solidapp.domain.model.CoinDetail
import com.example.solidapp.domain.use_case.coin.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private val _state: MutableLiveData<CoinDetailState> = MutableLiveData(CoinDetailState())
    val state: LiveData<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { getCoin(it) }
    }

    fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coinDetail = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}