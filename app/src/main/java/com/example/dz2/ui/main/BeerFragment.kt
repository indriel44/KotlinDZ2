package com.example.dz2.ui.main

import com.example.dz2.datalayer.IAccessor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dz2.R
import com.example.dz2.objects.Beer
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BeerFragment : Fragment() {
    private val viewModel by viewModels<MainViewModel>()
    private val beerAdapter = BeerAdapter(){}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.beer_item, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val beer: Beer = arguments?.getSerializable("key") as Beer

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val beer = withContext(Dispatchers.IO) { viewModel.getBeer(beer.Id())
                }
                val list: List<Beer> = listOf( beer )
                beerAdapter.submitList(list)


            } catch (error: Throwable) {

                error.printStackTrace()
            }
        }


    }
}
