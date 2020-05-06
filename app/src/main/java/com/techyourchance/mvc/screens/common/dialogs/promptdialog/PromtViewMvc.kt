package com.techyourchance.mvc.screens.common.dialogs.promptdialog

import com.techyourchance.mvc.screens.common.view.BaseObservableViewMvc

abstract class PromtViewMvc: BaseObservableViewMvc<PromtViewMvc.Listener>() {

    interface Listener {

        fun onPositiveButtonClicked()

        fun onNegativeButtonClicked()
    }

    abstract fun setTitle(text: String)

    abstract fun setMessage(text: String)

    abstract fun setPositiveButtonText(text: String)

    abstract fun setNegativeButtonText(text: String)

}