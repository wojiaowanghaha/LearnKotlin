package com.wojiaowanghaha.learnkotlin.viewmodel

import androidx.lifecycle.ViewModel

/**
 *
 * @author wanghaha
 * 创建日期：20-5-4
 * 描述：
 *
 */
class MainViewModel(countReserved :Int) :ViewModel(){
    var count = countReserved
}