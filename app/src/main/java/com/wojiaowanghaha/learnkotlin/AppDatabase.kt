package com.wojiaowanghaha.learnkotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wojiaowanghaha.learnkotlin.dao.UserDao
import com.wojiaowanghaha.learnkotlin.viewmodel.User

/**
 *
 * @author wanghaha
 * 创建日期：20-5-4
 * 描述：
 *
 */
@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let { return it }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database.db"
            ).build()
                .apply { instance = this }
        }
    }
}