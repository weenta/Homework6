package com.thoughtworks.didemo

sealed class ViewState {
    class Show(val txt: String) : ViewState()
    class Hide() : ViewState()
}