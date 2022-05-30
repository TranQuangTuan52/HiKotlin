package com.example.hikotlin.presenter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AuthPresenter {
    fun signUpWithEmailPassword(
        activity: AppCompatActivity,
        user: FirebaseAuth,
        email: String,
        password: String,
        confirmPassword: String,
        onRegisterSuccess: (message: String) -> Unit,
        onRegisterFailure: (message: String) -> Unit
    ) {
        if (email.isEmpty()) {
            onRegisterFailure("Email is not empty!")
        } else if (password.isEmpty()) {
            onRegisterFailure("Password is not empty!")
        } else if (password != confirmPassword) {
            onRegisterFailure("Confirm Password is incorrect!")
        } else {
            user.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        onRegisterSuccess("create successfully")
                    } else {
                        onRegisterFailure(task.exception?.message.toString())
                        println(task.exception.toString())
                    }
                }
        }
    }

    fun signInWithEmailPassword(
        activity: AppCompatActivity,
        user: FirebaseAuth,
        email: String,
        password: String,
        onSignInFailure: (message: String) -> Unit,
        onSignInSuccess: (message: String) -> Unit
    ) {
        if (email.isEmpty()) {
            onSignInFailure("Email is not empty!")
        } else if (password.isEmpty()) {
            onSignInFailure("Email is not empty!")
        } else {
            user.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        onSignInSuccess("Sign In Success!")
                    } else {
                        onSignInFailure(task.exception?.message.toString())
                    }

                }
        }
    }

    fun resetPasswordWithEmail(
        activity: AppCompatActivity,
        user: FirebaseAuth,
        email: String,
        onResetPasswordSuccess: (message: String) -> Unit,
        onResetPasswordFailure: (message: String) -> Unit
    ) {
        if (email.isEmpty()) {
            onResetPasswordFailure("Email is not empty!")
        }else{
            user.sendPasswordResetEmail(email).addOnCompleteListener(activity){task->
                if(task.isSuccessful){
                    onResetPasswordSuccess("Reset password success!\nPls check mail to create new password")
                }else{
                    onResetPasswordFailure(task.exception?.message.toString())
                }
            }
        }
    }
}