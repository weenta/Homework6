package com.thoughtworks.didemo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thoughtworks.didemo.MyViewModel
import com.thoughtworks.didemo.XRepo
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
class MyModule {
    @Singleton
    @Provides
    fun providesXRepo() = XRepo()

    @IntoMap
    @ViewModelKey(MyViewModel::class)
    @Provides
    //we can use @ClassKey, but we can't constraint key type to `ViewModel`, so we need to change to use Map<Class<*>, xxx> below
    fun providesViewModel(repo: XRepo): ViewModel =
        MyViewModel(repo)

    @Provides
    @Singleton
    //why we need @JvmSuppressWildcards https://github.com/google/dagger/issues/1478
    fun providersViewModelFactory(viewModelProviderMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return viewModelProviderMap[modelClass]?.get() as T
                    ?: throw IllegalStateException("Unsupported ViewModel")
            }
        }
}