package com.thoughtworks.didemo

import android.os.Handler
import android.os.Looper
import android.util.Log
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.concurrent.thread

class XRepo {
    private var response: ((String) -> Unit)? = null
    private val handle = Handler(Looper.getMainLooper()) {
        response?.invoke(it.obj as String)
        true
    }

    fun load(response: (String) -> Unit) {
        this.response = response
        thread {
            val message = handle.obtainMessage()
            message.obj = "loading..."
            message.sendToTarget()

            val request = ServiceBuilder.buildService(Advice::class.java)
            val call = request.getAdviceDetail()

            call.enqueue(object : Callback, retrofit2.Callback<AdviceInfo> {
                override fun onResponse(call: Call<AdviceInfo>, response: Response<AdviceInfo>) {
                    if (response.isSuccessful){
                        val message = handle.obtainMessage()
                        message.obj = response.body()?.slip?.advice
                        message.sendToTarget()
                    }
                }

                override fun onFailure(call: Call<AdviceInfo>, t: Throwable) {
                    val message = handle.obtainMessage()
                    message.obj = "connection failure, please try again!"
                    message.sendToTarget()
                }

            })
        }
    }
}