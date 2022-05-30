package com.example.hikotlin.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hikotlin.databinding.ActivityRegisterBinding
import com.example.hikotlin.model.CustomAlert
import com.example.hikotlin.presenter.AuthPresenter
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var user: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var authPresenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authPresenter = AuthPresenter()
        user = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener {
            createUser()
        }
        binding.textViewSignIn.setOnClickListener { navigateToSignInScreen() }
    }

    private fun createShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToSignInScreen() {
        val intent: Intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun onRegisterFailure(message: String) {
        CustomAlert.show(this, "Error", message)
    }

    private fun onRegisterSuccess(message: String) {
        CustomAlert.show(this, "dè dè dé de", message)
    }

    private fun createUser() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()

        authPresenter.signUpWithEmailPassword(
            this,
            user,
            email,
            password,
            confirmPassword,
            ::onRegisterFailure,
            ::onRegisterSuccess
        )


    }

}