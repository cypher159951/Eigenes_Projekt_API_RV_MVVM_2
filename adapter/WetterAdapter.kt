package com.example.eigenes_projekt_api_rv_mvvm.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eigenes_projekt_api_rv_mvvm.data.model.Wetter
import com.example.eigenes_projekt_api_rv_mvvm.databinding.ListItemWetterBinding
import com.example.eigenes_projekt_api_rv_mvvm.ui.WetterViewModel

class WetterAdapter(
    private val dataset: List<Wetter>,
    private val viewModel: WetterViewModel,
    val context: Context
)
    : RecyclerView.Adapter<WetterAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemWetterBinding) :
        RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ListItemWetterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }




    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        Log.d("Test","${item.location}")
        holder.binding.stadtTV.setText(item.location)
    }



    override fun getItemCount(): Int {
        return dataset.size
    }
}