package com.wojiaowanghaha.learnkotlin.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreatorBook {
    private const val BASE_URL = "https://itpanda.net/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass:Class<T>):T = retrofit.create(serviceClass)
}