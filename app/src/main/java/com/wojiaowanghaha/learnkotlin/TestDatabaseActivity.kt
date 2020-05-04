package com.wojiaowanghaha.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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









    }
}



