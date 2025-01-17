package com.wojiaowanghaha.learnkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wojiaowanghaha.learnkotlin.livedata.Repository

/**
 *
 * @author wanghaha
 * 创建日期：20-5-4
 * 描述：
 *
 */
class MainViewModel(countReserved: Int) : ViewModel() {

    val counter: LiveData<Int> get() = _counter

    private var _counter = MutableLiveData<Int>()

    private val  userLiveData = MutableLiveData<User>()
    private val  userIdLiveData = MutableLiveData<String>()

    val userName :LiveData<String> = Transformations.map(userLiveData){
        user -> "${user.fristName} ${user.lastName}"
    }

    val user:LiveData<User> = Transformations.switchMap(userIdLiveData){
        userId -> Repository.getUser(userId)
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun plusOneThread() {
        val count = _counter.value ?: 0
        _counter.postValue(count + 1)
    }

    fun clear() {
        _counter.value = 0
    }

    fun getUser(userId : String){
        userIdLiveData.value = userId
    }
}
