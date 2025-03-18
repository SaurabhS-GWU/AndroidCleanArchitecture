import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitectureapp.domain.usecase.GetCountriesUseCase
import com.example.cleanarchitectureapp.presentation.viewmodels.CountryListViewModel

class CountryViewModelFactory(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryListViewModel::class.java)) {
            return CountryListViewModel(getCountriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}