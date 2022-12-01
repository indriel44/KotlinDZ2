package com.example.dz2.businesslayer


import android.util.Log
import com.example.dz2.datalayer.IAccessor
import com.example.dz2.objects.Beer

class BeerProvider(private val accessor: IAccessor) {

    suspend fun getBeers(limit: Int): List<Beer> {
        return accessor.getBeers(limit)
    }

    suspend fun getBeer(id: String): List<Beer> {
        //Log.d("TEST", accessor.getBeer(id).Id())
        return accessor.getBeer(id)
    }
}