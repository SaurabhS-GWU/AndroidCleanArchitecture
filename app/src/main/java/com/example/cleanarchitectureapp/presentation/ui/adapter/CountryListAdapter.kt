import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitectureapp.R
import com.example.cleanarchitectureapp.domain.model.Country

class CountryAdapter(private val countryList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameText: TextView = view.findViewById(R.id.nameText)
        private val regionText: TextView = view.findViewById(R.id.regionText)
        private val codeText: TextView = view.findViewById(R.id.codeText)
        private val capitalText: TextView = view.findViewById(R.id.capitalText)

        fun bind(country: Country) {
            nameText.text = country.name
            regionText.text = country.region
            codeText.text = country.code
            capitalText.text = country.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country_layout, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int = countryList.size
}
