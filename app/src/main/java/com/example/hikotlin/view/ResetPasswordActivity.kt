package com.example.hikotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hikotlin.databinding.ActivityResetPasswordBinding
import com.example.hikotlin.model.CustomAlert
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

        binding.buttonResetPassword.setOnClickListener{onPressResetPassword()}
    }

    private fun onResetPasswordSuccess(message: String) {
        CustomAlert.show(this, "Success!", message)
    }

    private fun onResetPasswordFailure(message: String) {
        CustomAlert.show(this, "Error!", message)
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