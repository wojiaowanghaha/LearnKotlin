package com.wojiaowanghaha.learnkotlin.net

import java.lang.Exception

interface HttpCallbackListener {
    fun onFinish(response:String)
    fun onError(e:Exception)
}