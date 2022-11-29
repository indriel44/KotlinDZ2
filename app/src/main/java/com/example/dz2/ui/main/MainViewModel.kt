package com.example.dz2.ui.main

import androidx.lifecycle.ViewModel
import com.example.dz2.businesslayer.BeerProvider
import com.example.dz2.datalayer.IAccessor

//import com.example.dz2.ServiceLocator


class MainViewModel : ViewModel() {
    private val accessor = IAccessor.create()
    private val provider = BeerProvider(accessor)

    suspend fun getBeers() = provider.getBeers(100)
}