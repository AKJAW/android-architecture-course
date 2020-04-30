package com.techyourchance.mvc.screens.common.view.toolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import com.techyourchance.mvc.R
import com.techyourchance.mvc.screens.common.view.BaseViewMvc

class ToolbarViewMvc(layoutInflater: LayoutInflater, parent: ViewGroup?): BaseViewMvc() {
    override val rootView: View = layoutInflater.inflate(R.layout.layout_toolbar, parent, false)

    private val title: TextView = findViewById(R.id.txt_toolbar_title)

    fun setTitle(@StringRes id: Int) {
        title.text = getString(id)
    }
}