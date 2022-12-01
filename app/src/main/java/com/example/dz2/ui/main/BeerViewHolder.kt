package com.example.dz2.ui.main


import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.dz2.R
import com.example.dz2.objects.Beer
import kotlinx.coroutines.flow.callbackFlow

class BeerViewHolder(view: View,  val callback: (Beer) -> Unit) : RecyclerView.ViewHolder(view) {
    protected val image by lazy { view.findViewById<ImageView>(R.id.image) }
    protected val imageLoader by lazy { Picasso.get() }

    fun bind(beer: Beer) {
        val url = beer.imageId()
        imageLoader.load(url).into(image)
        val button =image
        button.setOnClickListener{ callback(beer)}

    }
}