package com.techyourchance.mvc.screens.common.dialogs.infodialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.screens.common.dialogs.BaseDialog

class InfoDialog: BaseDialog() {

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        private const val ARG_BUTTON_TEXT = "ARG_BUTTON_TEXT"

        fun newInfoDialog(title: String, message: String, buttonText: String): InfoDialog{
            val args = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_MESSAGE, message)
                putString(ARG_BUTTON_TEXT, buttonText)
            }
            val fragment = InfoDialog()
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var title: TextView
    private lateinit var message: TextView
    private lateinit var button: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arguments = arguments
        if(arguments == null || arguments.isEmpty){
            throw IllegalArgumentException("InfoDialog arguments cannot be empty")
        }

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_info)

        title = dialog.findViewById(R.id.dialog_title)
        message = dialog.findViewById(R.id.dialog_message)
        button = dialog.findViewById(R.id.dialog_button)

        title.text = arguments.getString(ARG_TITLE) ?: ""
        message.text = arguments.getString(ARG_MESSAGE) ?: ""
        button.text = arguments.getString(ARG_BUTTON_TEXT) ?: ""

        button.setOnClickListener {
            onButtonClicked()
        }

        return dialog
    }

    private fun onButtonClicked() {
        dismiss()
    }

}