package com.weather.easyweather.ui.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.easyweather.databinding.ItemWeatherIndexBinding

class IndexAdapter(
    private val context: Context,
    private val data: MutableList<String>?
) :
    RecyclerView.Adapter<IndexAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWeatherIndexBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    class ViewHolder(val binding: ItemWeatherIndexBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {

        }
    }

}