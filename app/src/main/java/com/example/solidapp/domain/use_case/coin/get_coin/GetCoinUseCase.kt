package com.example.solidapp.domain.use_case.coin.get_coin

import com.example.solidapp.common.Resource
import com.example.solidapp.data.remote.dto.toCoinDetail
import com.example.solidapp.domain.model.CoinDetail
import com.example.solidapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) { // If any other status other then 200 comes in response.
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) { // If internet or server isn't available
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}