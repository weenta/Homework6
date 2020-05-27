package com.thoughtworks.didemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(private val repo: XRepo) : ViewModel() {
    private val _result: MutableLiveData<ViewState> = MutableLiveData()
    val result: LiveData<ViewState> = _result
    fun load() {
        repo.load {
            if (it.isNotEmpty()) {
                _result.value = ViewState.Show(it)
            } else {
                _result.value = ViewState.Hide()
            }
        }
    }
}