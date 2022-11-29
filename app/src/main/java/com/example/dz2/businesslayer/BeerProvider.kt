package com.example.dz2.businesslayer

import com.google.gson.Gson
import com.example.dz2.datalayer.IAccessor
import com.example.dz2.objects.Beer

class BeerProvider(val accessor: IAccessor) {
    suspend fun getBeers(limit: Int): List<Beer> {
        return accessor.getBeers(limit)
    }
}