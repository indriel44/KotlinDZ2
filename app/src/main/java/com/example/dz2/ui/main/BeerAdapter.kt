package com.example.dz2.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.example.dz2.R
import com.example.dz2.objects.Beer

class BeerAdapter( val callback: (Beer) -> Unit) :
    ListAdapter<Beer, BeerViewHolder>(BeerDiffitemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        return BeerViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer = getItem(position)
        holder.bind(beer)
    }
}