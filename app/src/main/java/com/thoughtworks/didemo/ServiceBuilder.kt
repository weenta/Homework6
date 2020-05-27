package com.thoughtworks.didemo


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(10, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .build()

object ServiceBuilder {
    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.adviceslip.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}