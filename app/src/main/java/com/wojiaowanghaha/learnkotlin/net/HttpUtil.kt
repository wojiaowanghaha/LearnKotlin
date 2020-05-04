package com.wojiaowanghaha.learnkotlin.net

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Appendable
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {
    /**
     * 简单请求
     */
    fun sendHttpRequest(address: String): String {
        println(Thread.currentThread().name)
        var connection: HttpURLConnection? = null
        try {
            var response = StringBuilder()
            val url = URL(address)
            connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 8000
            connection.readTimeout = 8000
            val input = connection.inputStream
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    response.append(it)
                }
            }
            return response.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return e.message.toString()
        } finally {
            connection?.disconnect()
        }
    }

    /**
     * 添加接口回调
     */
    fun sendHttpRequest(address: String, listener: HttpCallbackListener) {
        thread {
            println(Thread.currentThread().name)
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                var url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 1000
                connection.readTimeout = 8000
                val input = connection.inputStream
                var reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                    listener.onFinish(response.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                listener.onError(e)
            }finally {
                connection?.disconnect()
            }
        }
    }

    /**
     * 使用Okhttp
     */
    fun sendOkhttpRequest(address: String,callback :okhttp3.Callback){
        val client  = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }





}


fun main() {
    var address = "https://www.baidu.com"
    val response = HttpUtil.sendHttpRequest(address)
    println(response.toString())

    HttpUtil.sendHttpRequest(address,object : HttpCallbackListener{
        override fun onFinish(response: String) {
            println(Thread.currentThread().name)
            println(response.toString())
        }
        override fun onError(e: java.lang.Exception) {
            println(e.printStackTrace().toString())
        }
    })

    HttpUtil.sendOkhttpRequest(address, object : Callback {
        override fun onFailure(call: Call, e: IOException) {
        }

        override fun onResponse(call: Call, response: Response) {
            println(Thread.currentThread().name)
            val responseData = response.body?.string()
            println(responseData.toString())
        }
    })
    var retrofit = Retrofit.Builder().baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var appService = retrofit.create(GitHubClient::class.java)
    appService.reposForuser("852172891").enqueue(object :retrofit2.Callback<List<GitHubRepo>>{
        override fun onFailure(call: retrofit2.Call<List<GitHubRepo>>, t: Throwable) {

        }

        override fun onResponse(
            call: retrofit2.Call<List<GitHubRepo>>,
            response: retrofit2.Response<List<GitHubRepo>>
        ) {
            println(response.body().toString())
        }
    })

    var appService2 = ServiceCreator.create(GitHubClient::class.java)
    appService2.reposForuser("852172891").enqueue(object :retrofit2.Callback<List<GitHubRepo>>{
        override fun onFailure(call: retrofit2.Call<List<GitHubRepo>>, t: Throwable) {

        }

        override fun onResponse(
            call: retrofit2.Call<List<GitHubRepo>>,
            response: retrofit2.Response<List<GitHubRepo>>
        ) {
            println(response.body().toString())
        }
    })

    var bookService = ServiceCreatorBook.create(BookClient::class.java)
    bookService.getBookByIdHeader("12").enqueue(object :retrofit2.Callback<
            ResponseBody>{
        override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
        }

        override fun onResponse(
            call: retrofit2.Call<ResponseBody>,
            response: retrofit2.Response<ResponseBody>
        ) {
            println(response.headers())
        }

    })


}