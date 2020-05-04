package com.wojiaowanghaha.learnkotlin.net

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path


interface BookClient {
    @GET("/book/{id}")
    fun getBookById(@Path("id") page: String?): Call<ResponseBody>

    @Headers("User-Agent:okhttp","Cache-Control:max-age=0")
    @GET("/book/{id}")
    fun getBookByIdHeader(@Path("id") page: String?): Call<ResponseBody>



}