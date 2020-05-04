package com.wojiaowanghaha.learnkotlin.viewmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author wanghaha
 * 创建日期：20-5-4
 * 描述：
 *
 */
@Entity
data class User(var fristName:String,var lastName:String,var age :Int){
    @PrimaryKey
    var id:Long =0
}