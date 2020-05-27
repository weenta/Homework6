package com.thoughtworks.didemo.di

import com.thoughtworks.didemo.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MyModule::class])
interface MyComponent {
    fun inject(activity: MainActivity)
}