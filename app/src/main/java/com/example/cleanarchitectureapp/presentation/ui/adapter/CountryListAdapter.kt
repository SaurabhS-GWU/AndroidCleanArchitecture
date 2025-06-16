import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitectureapp.R
import com.example.cleanarchitectureapp.domain.model.Country

class CountryAdapter(private val countryList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.cardView)
        private val nameText: TextView = itemView.findViewById(R.id.nameText)
        private val regionText: TextView = itemView.findViewById(R.id.regionText)
        private val codeText: TextView = itemView.findViewById(R.id.codeText)
        private val capitalText: TextView = itemView.findViewById(R.id.capitalText)

        init {
            cardView.setOnClickListener {
                val previousSelected = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousSelected)
                notifyItemChanged(selectedPosition)
                
                // Navigate to detail screen
                val country = countryList[adapterPosition]
                val bundle = Bundle().apply {
                    putParcelable("country", country)
                }
                itemView.findNavController().navigate(
                    R.id.action_countryListFragment_to_countryDetailFragment,
                    bundle
                )
            }
        }

        fun bind(country: Country) {
            nameText.text = country.name
            regionText.text = country.region
            codeText.text = country.code
            capitalText.text = country.capital

            // Update selection state
            cardView.isSelected = adapterPosition == selectedPosition
            cardView.setCardBackgroundColor(
                if (adapterPosition == selectedPosition) {
                    itemView.context.getColor(R.color.selected_item_background)
                } else {
                    itemView.context.getColor(android.R.color.white)
                }
            )
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
