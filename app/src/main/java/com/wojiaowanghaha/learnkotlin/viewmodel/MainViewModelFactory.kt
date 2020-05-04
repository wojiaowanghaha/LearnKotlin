package com.wojiaowanghaha.learnkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @author wanghaha
 * 创建日期：20-5-4
 * 描述：
 *
 */
class MainViewModelFactory (private val countReserved:Int):
    ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}