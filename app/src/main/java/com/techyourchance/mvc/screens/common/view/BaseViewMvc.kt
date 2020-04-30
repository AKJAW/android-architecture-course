package com.techyourchance.mvc.screens.common.view

import android.content.Context
import android.view.View
import androidx.annotation.StringRes


abstract class BaseViewMvc: ViewMvc {

    protected val context: Context
        get() = rootView.context

    protected fun <T: View> findViewById(id: Int): T = rootView.findViewById(id)

    protected fun getString(@StringRes id: Int) = context.getString(id)

}