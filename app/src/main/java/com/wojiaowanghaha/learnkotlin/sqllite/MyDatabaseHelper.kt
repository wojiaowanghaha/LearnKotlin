package com.wojiaowanghaha.learnkotlin.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 *
 * @author wanghaha
 * 创建日期：20-5-5
 * 描述：
 *
 */
class MyDatabaseHelper (var context: Context,name :String,version : Int) :
    SQLiteOpenHelper(context,name,null,version){
    private val createBook = "create table Book (" +"id integer primary key autoincrement,"+
            "author text,"+ "price  real ," + "pages integer,"+ "name text)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBook)
        Toast.makeText(context,"Create succeeded",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}