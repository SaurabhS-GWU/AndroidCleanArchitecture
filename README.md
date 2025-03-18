# AndroidCleanArchitecture
Android MVVM + Clean Architecture


Tech Stack
It follows Clean Architecture + MVVM, using: Views for UI
Retrofit for networking
Manual Dependency Injection Livedata for UI updates
Kotlin Coroutines for async operations

Project Structure:
com.example.cleanarchitectureapp
│── data (Handles API and Repository)
│ ├── model (Data models)
│ ├── remote (Retrofit API service)
│ ├── repository (Implements FetchCountriesRepository)
│── domain (Business Logic)
│ ├── model (Domain models)
│ ├── repository (FetchCountriesRepository interface)
│ ├── usecase (Business logic for fetching data)
│── presentation (UI & ViewModel)
│ ├── ui (Fragments)
│ ├── viewmodel (CountryListViewModel)
│── di (Manual Dependency Injection)
│── MainActivity.kt
