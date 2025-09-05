package com.weather.easyweather.ui.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.easyweather.R
import com.weather.easyweather.databinding.ItemLivingIndexBinding

class LivingIndexAdapter(
    private val context: Context,
    private val data: MutableList<String>?
) :
    RecyclerView.Adapter<LivingIndexAdapter.ViewHolder>() {

    private val others = mutableListOf<Pair<Int, String>>().apply {
        add(Pair(R.drawable.ic_living_dressing, "Dressing"))
        add(Pair(R.drawable.ic_living_comfort, "Comfort"))
        add(Pair(R.drawable.ic_living_cold, "ColdRisk"))
        add(Pair(R.drawable.ic_living_car_wash, "CarWashing"))
        add(Pair(0, ""))
        add(Pair(0, ""))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLivingIndexBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    class ViewHolder(val binding: ItemLivingIndexBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            icon.setImageResource(others[position].first)
            title.text = others[position].second
        }
    }

}