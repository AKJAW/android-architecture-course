package com.techyourchance.mvc.screens.common

interface ObservableViewMvc<Listener>: ViewMvc {

    fun registerListener(listener: Listener)

    fun unregisterListener(listener: Listener)

}