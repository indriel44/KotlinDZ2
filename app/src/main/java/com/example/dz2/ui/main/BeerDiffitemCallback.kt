package com.example.dz2.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.example.dz2.objects.Beer

class BeerDiffitemCallback: DiffUtil.ItemCallback<Beer>() {
    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Beer, newItem:Beer): Boolean {
        return oldItem.imageId() == newItem.imageId()
    }
}