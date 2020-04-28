package com.techyourchance.mvc.screens.common

import android.content.Context
import android.widget.Toast

class MessageDisplayer(private val context: Context){

    fun showToast(resId: Int){
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }

}