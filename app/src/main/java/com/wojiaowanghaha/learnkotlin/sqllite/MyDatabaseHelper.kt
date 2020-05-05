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
            "author text,"+ "price  real ," + "pages integer,"+ "category_id integer,"+"name text)"


    private val createCategory = "create table Category(" + "id integer primary key " +
            "autoincrement,"+ "category_name text," + "category_code integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBook)
        db.execSQL(createCategory)
        Toast.makeText(context,"Create succeeded",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if(oldVersion <= 1){
            db.execSQL(createCategory)
        }
        if(oldVersion <= 2){
            db.execSQL("alter table Book add column  category_id integer")
        }
    }

}