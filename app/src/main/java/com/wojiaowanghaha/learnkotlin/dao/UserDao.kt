package com.wojiaowanghaha.learnkotlin.dao

import androidx.room.*
import com.wojiaowanghaha.learnkotlin.viewmodel.User
import retrofit2.http.DELETE

/**
 *
 * @author wanghaha
 * 创建日期：20-5-4
 * 描述：
 *
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User):Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers():List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age : Int):List<User>


    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String):Int
}