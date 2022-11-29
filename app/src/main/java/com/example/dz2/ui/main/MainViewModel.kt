package com.example.dz2.ui.main

import androidx.lifecycle.ViewModel
import com.example.dz2.ServiceLocator

class MainViewModel : ViewModel() {
    private val provider = ServiceLocator.provider()

    suspend fun getBeers() = provider.getBeers(100)
}