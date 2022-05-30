package com.example.hikotlin.model

class User (
    var fullName: String,
    var age: Int?,
    var avatarUrl: String?,
    var email: String,
    var id: String?
) {

    public fun setUser(
        fullName: String,
        age: Int?,
        avatarUrl: String?,
        email: String,
        id: String?
    ) {
        this.fullName = fullName
        this.age = age
        this.avatarUrl = avatarUrl
        this.email = email
        this.id = id
    }

}