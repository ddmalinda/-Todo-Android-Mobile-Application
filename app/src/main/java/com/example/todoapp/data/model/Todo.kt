package com.example.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName="todos")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val userId:Int,
    val title:String,
    val description:String,
    val dueDate:String,
    val isCompleted:Boolean=false,
)