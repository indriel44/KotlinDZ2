package com.example.dz2

import android.app.Application

class BeerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ServiceLocator.initialize(this)
    }
}