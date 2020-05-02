package com.techyourchance.mvc.screens.common.controllers

interface BackPressedDispatcher{

    fun registerListener(listener: BackPressedListener)

    fun unregisterListener(listener: BackPressedListener)
}