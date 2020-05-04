package com.wojiaowanghaha.learnkotlin.viewmodel

import android.util.MutableDouble
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @author wanghaha
 * 创建日期：20-5-4
 * 描述：
 *
 */
class MainViewModel(countReserved :Int) :ViewModel(){

    var counter = MutableLiveData<Int>()

    init {
        counter.value = countReserved
    }
    fun plusOne(){
        val  count = counter.value ?: 0
        counter.value = count + 1
    }

    fun plusOneThread(){
        val count = counter.value ?: 0
        counter.postValue(count +1)
    }

    fun  clear(){
        counter.value = 0
    }
}
