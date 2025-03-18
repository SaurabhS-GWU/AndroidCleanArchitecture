package com.example.cleanarchitectureapp.data.repository

import com.example.cleanarchitectureapp.data.remote.FetchCountriesApi
import com.example.cleanarchitectureapp.domain.model.Country
import com.example.cleanarchitectureapp.domain.repository.FetchCountriesRepository
import kotlinx.coroutines.delay

class FetchCountriesRepositoryImpl(private val apiService: FetchCountriesApi) :
    FetchCountriesRepository {
    override suspend fun fetchCountries(): Result<List<Country>> {
        var attempt = 0
        // Initially we will start with a delay
        var delayTime = 1000L
        //maximum number of retries that will be allowed
        val maxRetries = 3

        while (attempt < maxRetries) {
            val response = apiService.fetchCountries()
            if (response.isSuccessful) {
                val countries = response.body()?.mapNotNull { dto ->
                    dto.name?.takeIf { it.isNotBlank() }?.let {
                        Country(name = it, code= dto.code ,capital = dto.capital, region = dto.region)
                    }
                } ?: emptyList()
                return Result.Success(countries)
            } else {
                attempt++
                if (attempt == maxRetries) {
                    return Result.Error("Failed to load data after $maxRetries attempts.")
                }
                delay(delayTime)
                delayTime *= 2 // Exponential backoff (1s → 2s → 4s)
            }
        }
        return Result.Error("Unexpected error occurred.")
    }
}