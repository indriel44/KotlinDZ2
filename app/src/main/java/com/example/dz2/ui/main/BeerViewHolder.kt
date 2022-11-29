package com.example.dz2.ui.main


import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.dz2.R
import com.example.dz2.objects.Beer

class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    protected val image by lazy { view.findViewById<ImageView>(R.id.image) }
    protected val imageLoader by lazy { Picasso.get() }

    fun bind(cat: Beer) {
        val url = cat.imageId()
        imageLoader.load(url).into(image)
    }
}