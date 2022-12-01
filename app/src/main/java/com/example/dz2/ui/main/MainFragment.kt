package com.example.dz2.ui.main

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
import com.example.dz2.objects.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Integer.parseInt

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private val beerAdapter = BeerAdapter() { Beer ->
        activity?.supportFragmentManager?.let {
            val fragment = BeerFragment().apply {
                val extras = Bundle().apply {
                    putSerializable("key",Beer)
                }
                arguments = extras
            }

            val transaction = it.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun try_reload(loading:ProgressBar, stubText:TextView){
        viewLifecycleOwner.lifecycleScope.launch {
            stubText.isVisible = false
            loading.isVisible = true

            try {
                val list = withContext(Dispatchers.IO) { viewModel.getBeers() }
                beerAdapter.submitList(list)
                loading.isVisible = false
                stubText.isVisible = false

            } catch (error: Throwable) {
                loading.isVisible = false
                stubText.isVisible = true
                stubText.text = (resources.getString(R.string.error) + error.message)
                Toast.makeText(
                    activity,
                    resources.getString(R.string.toast_fail),
                    Toast.LENGTH_SHORT
                ).show()
                error.printStackTrace()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager =
                GridLayoutManager(context, parseInt(resources.getString(R.string.columns)))
            adapter = beerAdapter
        }

        val loading = view.findViewById<ProgressBar>(R.id.ProgressBar)
        val stubText = view.findViewById<TextView>(R.id.stub)

        viewLifecycleOwner.lifecycleScope.launch {

            loading.isVisible = true

            try {
                val list = withContext(Dispatchers.IO) { viewModel.getBeers() }
                beerAdapter.submitList(list)
                loading.isVisible = false
                stubText.isVisible = false

            } catch (error: Throwable) {
                loading.isVisible = false
                stubText.isVisible = true
                stubText.text = (resources.getString(R.string.error) + error.message)
                Toast.makeText(
                    activity,
                    resources.getString(R.string.toast_fail),
                    Toast.LENGTH_SHORT
                ).show()
                error.printStackTrace()
                stubText.setOnClickListener { try_reload(loading, stubText) }
            }
        }

    }


    companion object {
        fun newInstance() = MainFragment()
    }
}