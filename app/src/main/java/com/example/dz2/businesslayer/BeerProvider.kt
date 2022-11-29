package com.example.dz2.businesslayer


import com.example.dz2.datalayer.IAccessor
import com.example.dz2.objects.Beer

class BeerProvider(private val accessor: IAccessor) {
    suspend fun getBeers(limit: Int): List<Beer> {
        return accessor.getBeers(limit)
    }
}