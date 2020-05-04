package com.wojiaowanghaha.learnkotlin.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wojiaowanghaha.learnkotlin.viewmodel.User

/**
 *
 * @author wanghaha
 * 创建日期：20-5-4
 * 描述：
 *
 */
object Repository {
    fun getUser(userId:String): LiveData<User> {
        val liveData  = MutableLiveData<User>()
        liveData.value = User(userId,userId,0)
        return  liveData
    }
}