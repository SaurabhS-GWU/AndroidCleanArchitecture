package com.example.cleanarchitectureapp.di

import com.example.cleanarchitectureapp.data.remote.FetchCountriesApi
import com.example.cleanarchitectureapp.data.repository.FetchCountriesRepositoryImpl
import com.example.cleanarchitectureapp.domain.repository.FetchCountriesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindFetchCountriesRepository(
        fetchCountriesRepositoryImpl: FetchCountriesRepositoryImpl
    ): FetchCountriesRepository

    companion object {
        private const val BASE_URL =
            "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): FetchCountriesApi {
            return retrofit.create(FetchCountriesApi::class.java)
        }
    }
}