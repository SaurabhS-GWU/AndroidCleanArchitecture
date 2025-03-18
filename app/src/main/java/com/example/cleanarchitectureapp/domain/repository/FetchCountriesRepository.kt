package com.example.cleanarchitectureapp.domain.repository

import com.example.cleanarchitectureapp.data.repository.Result
import com.example.cleanarchitectureapp.domain.model.Country

interface FetchCountriesRepository {
    suspend fun fetchCountries(): Result<List<Country>>
}