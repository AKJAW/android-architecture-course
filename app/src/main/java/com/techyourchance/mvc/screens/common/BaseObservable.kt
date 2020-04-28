package com.techyourchance.mvc.screens.common

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class BaseObservable <Listener> {

    private val listeners: MutableSet<Listener> = Collections.newSetFromMap(ConcurrentHashMap(1))

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    protected fun getListeners(): List<Listener> = listeners.toList()
}