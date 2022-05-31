package com.example.hikotlin.presenter

import com.example.hikotlin.model.App.Companion.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    private val retrofit by lazy {
    retrofit2.Retrofit.Builder().baseUrl(BASE_URL).converterFactories()

    }
}