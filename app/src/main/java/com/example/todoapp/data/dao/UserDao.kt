package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user:User)

    @Query("SELECT * FROM users WHERE username=:username LIMIT 1")
    suspend fun getUser(username:String):User?
}