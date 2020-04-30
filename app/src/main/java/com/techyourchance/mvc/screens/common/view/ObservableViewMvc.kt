package com.techyourchance.mvc.screens.common.view

interface ObservableViewMvc<Listener>: ViewMvc {

    fun registerListener(listener: Listener)

    fun unregisterListener(listener: Listener)

}