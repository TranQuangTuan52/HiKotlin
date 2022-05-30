package com.example.hikotlin.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hikotlin.databinding.ActivitySignInBinding
import com.example.hikotlin.model.CustomAlert
import com.google.firebase.auth.FirebaseAuth
import com.example.hikotlin.presenter.AuthPresenter


class SignInActivity : AppCompatActivity() {

    private lateinit var user: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding
    private lateinit var authPresenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        user = FirebaseAuth.getInstance()
        authPresenter = AuthPresenter()
        setContentView(binding.root)

        binding.buttonSignIn.setOnClickListener { onPressSignIn() }
        binding.textViewForgotPassword.setOnClickListener{ onPressForgotPassword()}
        binding.textViewRegisterAccount.setOnClickListener { onPressRegister() }
    }

    private fun onPressRegister(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun onPressForgotPassword() {
        val intent = Intent(this, ResetPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun onSignInSuccess(message: String) {
        CustomAlert.show(this, "dè dè dé de", message)
    }

    private fun onSignInFailure(message: String) {
        CustomAlert.show(this, "Error", message)
    }

    private fun onPressSignIn() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        authPresenter.signInWithEmailPassword(
            this,
            user,
            email,
            password,
            ::onSignInFailure,
            ::onSignInSuccess
        )
    }




}