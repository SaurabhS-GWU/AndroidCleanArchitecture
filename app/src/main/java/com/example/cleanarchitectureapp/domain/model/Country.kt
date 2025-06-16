package com.example.cleanarchitectureapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String,
    val capital: String,
    val region: String,
    val code: String,
    val population: Long = 0,
    val area: Double = 0.0,
    val languages: List<String> = emptyList(),
    val flagUrl: String = "https://flagcdn.com/w320/${code.lowercase()}.png"
) : Parcelable
