package com.example.cleanarchitectureapp.domain.usecase
import com.example.cleanarchitectureapp.data.repository.Result
import com.example.cleanarchitectureapp.domain.model.Country
import com.example.cleanarchitectureapp.domain.repository.FetchCountriesRepository

class GetCountriesUseCase(private val repository: FetchCountriesRepository) {
    suspend fun execute(): Result<List<Country>> {
        return repository.fetchCountries()
    }
}