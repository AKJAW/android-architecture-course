package com.techyourchance.mvc.screens.common

import android.content.Context
import android.view.View


abstract class BaseViewMvc: ViewMvc {

    protected val context: Context
        get() = rootView.context

    protected fun <T: View> findViewById(id: Int): T = rootView.findViewById(id)

}