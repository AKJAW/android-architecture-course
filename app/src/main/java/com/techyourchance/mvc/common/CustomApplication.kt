package com.techyourchance.mvc.common

import android.app.Application
import com.techyourchance.mvc.common.dependencyinjection.CompositionRoot

class CustomApplication: Application(){

    lateinit var compositionRoot: CompositionRoot
        private set

    override fun onCreate() {
        super.onCreate()
        compositionRoot = CompositionRoot()
    }
}