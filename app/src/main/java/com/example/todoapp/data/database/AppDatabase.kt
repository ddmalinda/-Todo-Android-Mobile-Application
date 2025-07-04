package com.example.todoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.data.dao.ToDODao
import com.example.todoapp.data.dao.UserDao
import com.example.todoapp.data.model.Todo
import com.example.todoapp.data.model.User

@Database(entities = [User::class,Todo::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun todoDao():ToDODao

    companion object{
        @Volatile
        private var INSTANCE:AppDatabase?=null

        fun getDAtaBase(context: Context):AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_database"
                )
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}