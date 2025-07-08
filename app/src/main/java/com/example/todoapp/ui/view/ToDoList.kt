package com.example.todoapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.data.database.AppDatabase
import com.example.todoapp.databinding.ActivityTodoListBinding
import com.example.todoapp.ui.adapter.TodoAdapter
import kotlinx.coroutines.launch


class ToDoList : AppCompatActivity() {
    companion object {
         const val USERID = "com.example.todoapp.USERID"
    }
    private lateinit var binding: ActivityTodoListBinding
    private lateinit var todoAdapter: TodoAdapter
    private val db by lazy { AppDatabase.getDataBase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        lifecycleScope.launch {
            val userId: Int = intent.getIntExtra(ToDoList.USERID, -1)

            db.todoDao().getTodosForUser(userId).observe(this@ToDoList) { todos ->
                todoAdapter.submitList(todos)
            }

            binding.fab.setOnClickListener {
                val intent = Intent(this@ToDoList, AddEditTodoActivity::class.java)
                intent.putExtra(AddEditTodoActivity.USERID,userId)
                startActivity(intent)
            }
        }

    }
    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter()
        binding.recyclerView.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@ToDoList)
        }
    }
}
