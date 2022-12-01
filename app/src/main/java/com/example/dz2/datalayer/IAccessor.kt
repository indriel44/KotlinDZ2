package com.example.dz2.datalayer


import android.util.Log
import com.example.dz2.objects.Beer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IAccessor {
    @GET("/v2/beers")
    suspend fun getBeers(@Query("abv_lt") limit: Int): List<Beer>
    @GET("/v2/beers/{id}")
    suspend fun getBeer( @Path("id") id:String): List<Beer>

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