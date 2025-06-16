package com.example.cleanarchitectureapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cleanarchitectureapp.R
import com.example.cleanarchitectureapp.domain.model.Country
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    private lateinit var imageViewFlag: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewCapital: TextView
    private lateinit var textViewPopulation: TextView
    private lateinit var textViewRegion: TextView
    private lateinit var textViewLanguages: TextView
    private lateinit var textViewArea: TextView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setupToolbar()
        setupSystemWindowInsets(view)
        displayCountryDetails()
    }

    private fun initializeViews(view: View) {
        imageViewFlag = view.findViewById(R.id.imageViewFlag)
        textViewName = view.findViewById(R.id.textViewName)
        textViewCapital = view.findViewById(R.id.textViewCapital)
        textViewPopulation = view.findViewById(R.id.textViewPopulation)
        textViewRegion = view.findViewById(R.id.textViewRegion)
        textViewLanguages = view.findViewById(R.id.textViewLanguages)
        textViewArea = view.findViewById(R.id.textViewArea)
        toolbar = view.findViewById(R.id.toolbar)
    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupSystemWindowInsets(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = insets.top)
            windowInsets
        }
    }

    private fun displayCountryDetails() {
        arguments?.getParcelable<Country>("country")?.let { country ->
            // Load flag image with Glide
            Glide.with(this)
                .load(country.flagUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(imageViewFlag)

            // Set country details
            textViewName.text = country.name
            textViewCapital.text = "Capital: ${country.capital}"
            textViewPopulation.text = "Population: ${country.population}"
            textViewRegion.text = "Region: ${country.region}"
            textViewLanguages.text = "Languages: ${country.languages.joinToString(", ")}"
            textViewArea.text = "Area: ${country.area} km²"
        }
    }
} 