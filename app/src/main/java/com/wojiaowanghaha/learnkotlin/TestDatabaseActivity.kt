package com.wojiaowanghaha.learnkotlin

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wojiaowanghaha.learnkotlin.sqllite.MyDatabaseHelper
import com.wojiaowanghaha.learnkotlin.viewmodel.User
import kotlinx.android.synthetic.main.activity_test_database.*
import kotlin.concurrent.thread

class TestDatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_database)
        var userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom","Brady",40)
        val user2 = User("Tom","Hanks",63)

        val dbHelper = MyDatabaseHelper(this,"BookStore.db",1)

        addUserBtn.setOnClickListener {
            thread {
//                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }


        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()){
                    Log.d(this.javaClass.name,user.toString())
                }
            }
        }

        crateDatabaseSqLite.setOnClickListener {
            dbHelper.writableDatabase
        }

        addDataSQLLiteBtn.setOnClickListener {
          val db =  dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                //开始组装第一条数据
                put("name","The Da Vinci  Code")
                put("author","Dan Brown")
                put("pages",520)
                put("price",19.93)
            }
            db.insert("Book", null,values1)

            val value2 = ContentValues().apply {
                //开始组装第二条数据
                put("name","The Lost Symbol")
                put("author","Dan Brown")
                put("pages",510)
                put("price",19.95)
            }
            db.insert("Book",null,value2)
        }
        queryDataSQLLiteBtn.setOnClickListener {
            val db = dbHelper.writableDatabase
            //查询数据
            val cursor = db.query("Book",null,null,
                null,null,null,null)
            if(cursor.moveToFirst()){
                do{
                    //遍历Cursor对象，取出数据并打印
                    val  name = cursor.getString(cursor.getColumnIndex("name"))
                    val  author = cursor.getString(cursor.getColumnIndex("author"))
                    val  pages  = cursor.getInt(cursor.getColumnIndex("pages"))
                    val  price  = cursor.getDouble(cursor.getColumnIndex("price"))

                    Log.d(this.javaClass.name,"book name is $name pages is $pages price is" +
                            "$price author is $author"  )

                }while (cursor.moveToNext())
            }
            cursor.close()
        }
        updateDataSQLLitelBtn.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value = ContentValues()
            value.put("price",10.99)
            db.update("Book",value,"name = ?", arrayOf("The Da Vinci  Code"))
        }

        deleteDataSqlLiteBtn.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book","pages >?", arrayOf("70"))
        }

        replaceDataSqliteBtn.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()

            try {
                db.delete("Book",null,null)
                //手动抛出一个异常，让事务失败
//                    throw NullPointerException()
                val values = ContentValues().apply {
                    put("name","Game of Thrones")
                    put("author","Game of Thrones")
                    put("pages","George Martin")
                    put("price",20.85)
                }
                db.insert("Book",null,values)
                //事务已经执行成功
                db.setTransactionSuccessful()
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                db.endTransaction()
            }
        }
    }
}



