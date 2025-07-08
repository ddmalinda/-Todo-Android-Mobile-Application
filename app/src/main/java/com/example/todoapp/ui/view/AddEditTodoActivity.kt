package com.example.todoapp.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todoapp.data.database.AppDatabase
import com.example.todoapp.data.model.Todo
import com.example.todoapp.databinding.ActivityAddEditTodoBinding
import kotlinx.coroutines.launch

class AddEditTodoActivity :AppCompatActivity(){
    companion object {
        const val USERID = "com.example.todoapp.USERID"
    }

    private lateinit var binding: ActivityAddEditTodoBinding
    private val db by lazy {AppDatabase.getDataBase(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()
            val dueDate = binding.editTextDueDate.text.toString().trim()
            if(title.isEmpty()){
                Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                val userId: Int = intent.getIntExtra(ToDoList.USERID, -1)
                val newTodo = Todo(userId = userId, title = title, description = description, dueDate = dueDate)
                db.todoDao().insert(newTodo)
                finish()
            }

        }
    }

}