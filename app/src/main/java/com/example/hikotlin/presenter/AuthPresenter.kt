package com.example.hikotlin.presenter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hikotlin.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class AuthPresenter {

    private val db = Firebase.firestore
    private val userData: User = User.user

    fun getUserInfoById(uid: String) {
        db.collection("Users").get().addOnSuccessListener { result ->
            for (document in result) {
                val user = document.toObject<User>()
                userData.setData(
                    fullName = user.fullName,
                    email = user.email,
                    age = null,
                    uid = user.uid,
                    avatarUrl = user.avatarUrl
                )
            }
        }
            .addOnFailureListener { exception ->
                Log.w("AUTH", "Error getting documents.", exception)
            }
    }


    fun signUpWithEmailPassword(
        context: AppCompatActivity,
        user: FirebaseAuth,
        fullName: String,
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
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        val fsUser = hashMapOf<String, Any>()
                        val uid = user.currentUser?.uid.toString()
                        fsUser["fullName"] = fullName
                        fsUser["email"] = email
                        fsUser["uid"] = uid
                        db.collection("Users").add(fsUser).addOnCompleteListener { documentsRef ->
                            if (documentsRef.isSuccessful) {
                                userData.setData(
                                    fullName = fullName,
                                    email = email,
                                    uid = uid,
                                    age = null,
                                    avatarUrl = null
                                )
                                onRegisterSuccess("create successfully :3")
                            } else {
                                onRegisterFailure(documentsRef.exception?.message.toString())
                            }
                        }

                    } else {
                        onRegisterFailure(task.exception?.message.toString())
                        println(task.exception.toString())
                    }
                }
        }
    }


    fun signInWithEmailPassword(
        context: AppCompatActivity,
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
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {
                        println(task.result.user)
                        onSignInSuccess("Sign In Success!")
                        getUserInfoById(task.result.user?.uid.toString())
                        userData.setData(
                            fullName = "fullName",
                            email = email,
                            uid = task.result.user?.uid,
                            age = null,
                            avatarUrl = null
                        )
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
        } else {
            user.sendPasswordResetEmail(email).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    onResetPasswordSuccess("Reset password success!\nPls check mail to create new password")
                } else {
                    onResetPasswordFailure(task.exception?.message.toString())
                }
            }
        }
    }
}
