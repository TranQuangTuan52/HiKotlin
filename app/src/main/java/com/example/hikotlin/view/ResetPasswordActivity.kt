package com.example.hikotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hikotlin.databinding.ActivityResetPasswordBinding
import com.example.hikotlin.presenter.AuthPresenter
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var user: FirebaseAuth
    private lateinit var authPresenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        user = FirebaseAuth.getInstance()
        authPresenter = AuthPresenter()
        setContentView(binding.root)


    }

    private fun createShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onResetPasswordSuccess(message: String) {
        createShortToast(message)
    }

    private fun onResetPasswordFailure(message: String) {
        createShortToast(message)
    }

    private fun onPressResetPassword() {
        val email: String = binding.editTextEmail.text.toString()
        authPresenter.resetPasswordWithEmail(
            this,
            user,
            email,
            ::onResetPasswordSuccess,
            ::onResetPasswordFailure
        )
    }
}