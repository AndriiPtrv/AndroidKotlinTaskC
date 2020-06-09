package com.example.androidkotlintaskc.network

import com.example.androidkotlintaskc.network.model.FactOfCat
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object ApiService {
    private const val API = "https://cat-fact.herokuapp.com/facts/"
    private var privateApi: PrivateApi

    fun getData(): Call<List<FactOfCat>> {
        return privateApi.getData(100)
    }

    interface PrivateApi {
        @GET("random")
        fun getData(
            @Query("amount") amount: Int
        ): Call<List<FactOfCat>>
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API)
            .client(client)
            .build()
        privateApi = retrofit.create(PrivateApi::class.java)
    }
}