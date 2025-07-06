package com.example.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    //real time update in UI
    @Query("SELECT * FROM todos ORDER BY dueData ASC")
    fun getAllTodos():Flow<List<Todo>>

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}