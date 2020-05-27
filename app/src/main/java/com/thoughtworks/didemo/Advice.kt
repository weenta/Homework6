package com.thoughtworks.didemo

import retrofit2.Call
import retrofit2.http.GET

interface Advice {
    @GET("advice")
    fun getAdviceDetail(): Call<AdviceInfo>
}