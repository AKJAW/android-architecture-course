package com.techyourchance.mvc.screens.common.controllers

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.mvc.common.CustomApplication
import com.techyourchance.mvc.common.dependencyinjection.ControllerCompositionRoot

abstract class BaseActivity : AppCompatActivity() {

    protected val compositionRoot: ControllerCompositionRoot by lazy {
        createCompositionRoot()
    }

    private fun createCompositionRoot(): ControllerCompositionRoot {
        val root = (application as CustomApplication).compositionRoot
        return ControllerCompositionRoot(root, this)
    }

}