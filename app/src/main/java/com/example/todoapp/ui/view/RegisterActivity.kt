package com.example.todoapp.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todoapp.data.database.AppDatabase
import com.example.todoapp.data.model.User
import com.example.todoapp.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

class RegisterActivity :AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val db by lazy { AppDatabase.getDataBase(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    //check if user already exists
                    if(db.userDao().getUser(username)==null){
                        db.userDao().insert(User(username = username, password = password))
                        Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@RegisterActivity, "Username already taken", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}