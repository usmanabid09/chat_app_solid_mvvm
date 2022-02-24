package com.example.solidapp.di

import android.content.Context
import com.example.solidapp.MainApplication
import com.example.solidapp.common.Constants.BASE_URL
import com.example.solidapp.data.remote.CoinApi
import com.example.solidapp.data.repository.CoinRepositoryImpl
import com.example.solidapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainApplication = app as MainApplication

    @Provides
    @Singleton
    fun provideCoinApi(): CoinApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

}