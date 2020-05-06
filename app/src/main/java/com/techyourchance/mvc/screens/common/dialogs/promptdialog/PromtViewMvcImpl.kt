package com.techyourchance.mvc.screens.common.dialogs.promptdialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.techyourchance.mvc.R

class PromtViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) : PromtViewMvc() {

    override val rootView: View = inflater.inflate(R.layout.dialog_prompt, parent, false)

    private val title: TextView = findViewById(R.id.dialog_title)
    private val message: TextView = findViewById(R.id.dialog_message)
    private val positiveButton: Button = findViewById(R.id.dialog_positive_button)
    private val negativeButton: Button = findViewById(R.id.dialog_negative_button)

    init {
        positiveButton.setOnClickListener {
            getListeners().forEach {
                it.onPositiveButtonClicked()
            }
        }

        negativeButton.setOnClickListener {
            getListeners().forEach {
                it.onNegativeButtonClicked()
            }
        }
    }

    override fun setTitle(text: String) {
        title.text = text
    }

    override fun setMessage(text: String) {
        message.text = text
    }

    override fun setPositiveButtonText(text: String) {
        positiveButton.text = text
    }

    override fun setNegativeButtonText(text: String) {
        negativeButton.text = text
    }

}