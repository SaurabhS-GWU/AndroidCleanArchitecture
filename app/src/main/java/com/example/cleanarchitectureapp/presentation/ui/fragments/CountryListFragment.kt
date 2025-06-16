package com.example.cleanarchitectureapp.presentation.ui.fragments

import CountryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.cleanarchitectureapp.R
import com.example.cleanarchitectureapp.presentation.viewmodels.CountryListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment: Fragment() {

    private lateinit var recyclerViewCountries: RecyclerView
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var textViewEmptyState: TextView
    private val viewModel: CountryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recyclerViewCountries = view.findViewById(R.id.recyclerViewCountries)
        textViewEmptyState = view.findViewById(R.id.textViewEmptyState)

        initObservers()
        viewModel.fetchCountries()
    }

    private fun initObservers() {
        // Observe the LiveData from ViewModel
        viewModel.countries.observe(viewLifecycleOwner) { countryList ->
            countryAdapter = CountryAdapter(countryList)
            recyclerViewCountries.adapter = countryAdapter
        }

        // Observe errors if any
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            // Handle errors
            recyclerViewCountries.visibility = View.GONE
            textViewEmptyState.visibility = View.VISIBLE
            textViewEmptyState.text = errorMessage
            println("Error: $errorMessage")
        }
    }
}