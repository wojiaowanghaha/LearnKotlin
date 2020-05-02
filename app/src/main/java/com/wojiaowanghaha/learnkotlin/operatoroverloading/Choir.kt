package com.wojiaowanghaha.learnkotlin.operatoroverloading

import android.util.Log

class Choir {
    private val singers = mutableListOf<String>()

    fun addSinger(singer:Singer){
        singers.add(singer.toString());
    }

    operator fun plusAssign(singer: Singer){
        singers.add(singer.toString())
    }

}

data class Singer(val name:String)

fun  main(){
    var choir  = Choir()
    var singerMeghan = Singer("Meghan")
    var singerMeghan2 = Singer("Meghan2")
    choir.addSinger(singerMeghan)
    choir.addSinger(singerMeghan2)

    choir += singerMeghan
}