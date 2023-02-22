package com.pedroid1.graphqlproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedroid1.graphqlproject.databinding.CountryItemBinding
import com.pedroid1.graphqlproject.domain.model.SimpleCountry

class CountriesAdapter(
    private val countriesList: List<SimpleCountry>,
    private val onSelectCountry: OnSelectCountry
) :
    RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    class CountriesViewHolder(private val _binding: CountryItemBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        fun bind(currentCountry: SimpleCountry, listener: OnSelectCountry) {
            _binding.country = currentCountry
            _binding.onClick = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countriesList[position], onSelectCountry)
    }

    interface OnSelectCountry {
        fun onSelected(code: String)
    }
}
