package com.techyourchance.mvc.screens.common.messages

import android.content.Context
import android.widget.Toast

class ToastHelper(private val context: Context){

    fun show(resId: Int){
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }

}