package com.morozco.mycaw.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morozco.mycaw.databinding.CountryItemBinding
import com.morozco.mycaw.network.ItemResponse

class CountriesAdapter: RecyclerView.Adapter<CountriesAdapter.CountryHolder>() {

    private var countryList = emptyList<ItemResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bin(countryList[position])
    }
    override fun getItemCount() = countryList.size

    fun addCountries(newEpisodesList: List<ItemResponse>) {
        countryList = newEpisodesList
        this.notifyDataSetChanged()
    }
    inner class CountryHolder(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bin(countryItem: ItemResponse) = with(binding) {
            binding.apply {
                country = countryItem
            }
        }
    }
}