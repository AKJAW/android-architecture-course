package com.techyourchance.mvc.screens.common.controllers

import androidx.fragment.app.Fragment
import com.techyourchance.mvc.common.CustomApplication
import com.techyourchance.mvc.common.dependencyinjection.ControllerCompositionRoot

abstract class BaseFragment: Fragment() {

    protected val compositionRoot: ControllerCompositionRoot by lazy {
        createCompositionRoot()
    }

    private fun createCompositionRoot(): ControllerCompositionRoot {
        val root = (requireActivity().application as CustomApplication).compositionRoot
        return ControllerCompositionRoot(root, requireActivity())
    }
}