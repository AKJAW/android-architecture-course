package com.techyourchance.mvc.screens.common.view.drawer

import android.widget.FrameLayout
import com.techyourchance.mvc.screens.common.view.ObservableViewMvc

interface NavDrawerViewMvc: ObservableViewMvc<NavDrawerViewMvc.Listener> {

    interface Listener {
        fun onQuestionsClicked()
    }

    val fragmentFrame: FrameLayout

    fun isDrawerOpen(): Boolean

    fun openDrawer()

    fun closeDrawer()
}