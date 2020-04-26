package com.techyourchance.mvc.screens.common

abstract class BaseObservableViewMvc<Listener>
    : BaseViewMvc(), ObservableViewMvc<Listener>{

    private val listeners = hashSetOf<Listener>()

    override fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    protected fun getListeners(): List<Listener> = listeners.toList()
}