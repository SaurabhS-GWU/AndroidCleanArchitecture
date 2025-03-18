package com.example.cleanarchitectureapp.data.remote

import com.example.cleanarchitectureapp.data.model.CountryDto
import retrofit2.Response
import retrofit2.http.GET

interface FetchCountriesApi {
    @GET("countries.json")
    suspend fun fetchCountries(): Response<List<CountryDto>>
}