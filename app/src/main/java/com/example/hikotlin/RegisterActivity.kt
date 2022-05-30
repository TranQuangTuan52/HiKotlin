package com.example.hikotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hikotlin.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var user: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Auth", "onCreate")

        user = FirebaseAuth.getInstance()
        binding.buttonSignUp.setOnClickListener {
            createUser()
        }

    }

    private fun createShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun createUser() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val cofirmPassword = binding.editTextConfirmPassword.text.toString()

        if (email.isEmpty()) {
            createShortToast("Email is not empty!")
        } else if (password.isEmpty()) {
            createShortToast("Password is not empty!")
        } else if (!password.equals(cofirmPassword)) {
            createShortToast("Confirm Password is incorrect!")
        } else {
            createShortToast("dè dè dé de")
            user.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("Auth", "createUser: Success ")
                        createShortToast("create successfully")
                    }else{
                        createShortToast("Error: ${task.exception}")
                        Log.e("Auth", "createUser Failure: ${task.exception}")
                    }
                }
        }
    }

}