package com.example.dz2.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dz2.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private val beerAdapter = BeerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = beerAdapter
        }

        val loading = view.findViewById<ProgressBar>(R.id.ProgressBar)
        val stubText = view.findViewById<TextView>(R.id.stub)


        viewLifecycleOwner.lifecycleScope.launch {
            loading.isVisible = true
            delay(2000)
            try {
                val list = withContext(Dispatchers.IO) { viewModel.getBeers() }
                beerAdapter.submitList(list)
                loading.isVisible = false
                stubText.isVisible = false

            } catch (error: Throwable) {
                loading.isVisible = false
                stubText.isVisible = true
                stubText.text = "Error: ${error.message}"
                error.printStackTrace()
                stubText.setOnClickListener {
                    Toast.makeText(activity, "Try again later", Toast.LENGTH_SHORT).show()
                }

            }


        }
    }


    companion object {
        fun newInstance() = MainFragment()
    }
}