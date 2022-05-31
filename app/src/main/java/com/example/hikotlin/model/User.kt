package com.example.hikotlin.model

class User private constructor() {
    var fullName: String = ""
    var age: Int? = null
    var avatarUrl: String? = null
    var email: String = ""
    var uid: String? = null

    fun setData(
        fullName: String,
        email: String,
        age: Int?,
        avatarUrl: String?,
        uid: String?,
    ) {
        this.fullName = fullName
        this.age = age
        this.avatarUrl = avatarUrl
        this.email = email
        this.uid = uid
    }

    companion object {
        val user = User()
    }


}