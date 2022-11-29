package com.example.dz2.datalayer


import com.example.dz2.objects.Beer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IAccessor {
    @GET("/v2/beers")
    suspend fun getBeers(@Query("abv_lt") limit: Int): List<Beer>


    companion object {
        fun create(): IAccessor {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().apply {
                addNetworkInterceptor(loggingInterceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl("https://api.punkapi.com")
            }.build()

            return retrofit.create(IAccessor::class.java)
        }
    }
}