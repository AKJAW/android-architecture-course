package com.techyourchance.mvc.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.techyourchance.mvc.common.CustomApplication
import com.techyourchance.mvc.common.dependencyinjection.ControllerCompositionRoot

abstract class BaseDialog: DialogFragment() {

    protected val compositionRoot: ControllerCompositionRoot by lazy {
        createCompositionRoot()
    }

    private fun createCompositionRoot(): ControllerCompositionRoot {
        val root = (requireActivity().application as CustomApplication).compositionRoot
        return ControllerCompositionRoot(requireActivity(), root)
    }
}