package com.example.cleanarchitectureapp.data.model

import com.google.gson.annotations.SerializedName

data class CountryDto (
    @SerializedName("name")
    val name: String,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("code")
    val code: String
)