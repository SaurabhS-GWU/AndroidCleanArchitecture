package com.example.cleanarchitectureapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureapp.data.repository.Result
import com.example.cleanarchitectureapp.domain.model.Country
import com.example.cleanarchitectureapp.domain.usecase.GetCountriesUseCase
import kotlinx.coroutines.launch

class CountryListViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            when (val result = getCountriesUseCase.execute()) {
                is Result.Success -> {
                    _countries.value = result.data // Update LiveData with list of countries
                }
                is Result.Error -> {
                    _error.value = "Failed to fetch countries" // Show error message if failure
                }
            }
        }
    }

}