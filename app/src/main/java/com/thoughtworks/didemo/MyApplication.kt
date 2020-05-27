package com.thoughtworks.didemo

import android.app.Application
import com.thoughtworks.didemo.di.DaggerMyComponent
import com.thoughtworks.didemo.di.MyComponent

class MyApplication : Application() {
    lateinit var component: MyComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerMyComponent.create()
    }
}