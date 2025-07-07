package com.example.todoapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todoapp.databinding.ActivityLoginBinding
import com.example.todoapp.data.database.AppDatabase
import kotlinx.coroutines.launch

class LoginActivity:AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val db by lazy { AppDatabase.getDataBase(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val username=binding.editTextUsername.text.toString().trim()
            val password=binding.editTextPassword.text.toString().trim()


            if(username.isEmpty()&&password.isEmpty()){
                binding.editTextUsername.error="Username is required"
                binding.editTextPassword.error="Password is required"
            }else{
                lifecycleScope.launch {
                    val user = db.userDao().getUser(username)
                    if(user != null && user.password == password) {
                        val intent =Intent(this@LoginActivity, AddEditTodoActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}