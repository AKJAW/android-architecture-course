package com.techyourchance.mvc.screens.common.dialogs.promptdialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.screens.common.dialogs.BaseDialog
import com.techyourchance.mvc.screens.common.dialogs.DialogsEventBus

class PromptDialog: BaseDialog() {

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        private const val ARG_POSITIVE_BUTTON_TEXT = "ARG_POSITIVE_BUTTON_TEXT"
        private const val ARG_NEGATIVE_BUTTON_TEXT = "ARG_NEGATIVE_BUTTON_TEXT"

        fun newPromptDialog(
                title: String,
                message: String,
                negativeButtonText: String,
                positiveButtonText: String
        ): PromptDialog{
            val args = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_MESSAGE, message)
                putString(ARG_NEGATIVE_BUTTON_TEXT, negativeButtonText)
                putString(ARG_POSITIVE_BUTTON_TEXT, positiveButtonText)
            }
            val fragment = PromptDialog()
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var eventBus: DialogsEventBus

    private lateinit var title: TextView
    private lateinit var message: TextView
    private lateinit var positiveButton: Button
    private lateinit var negativeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        eventBus = compositionRoot.dialogEventBus
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arguments = arguments
        if(arguments == null || arguments.isEmpty){
            throw IllegalArgumentException("InfoDialog arguments cannot be empty")
        }

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_prompt)

        title = dialog.findViewById(R.id.dialog_title)
        message = dialog.findViewById(R.id.dialog_message)
        negativeButton = dialog.findViewById(R.id.dialog_negative_button)
        positiveButton = dialog.findViewById(R.id.dialog_positive_button)

        title.text = arguments.getString(ARG_TITLE) ?: ""
        message.text = arguments.getString(ARG_MESSAGE) ?: ""
        negativeButton.text = arguments.getString(ARG_NEGATIVE_BUTTON_TEXT) ?: ""
        positiveButton.text = arguments.getString(ARG_POSITIVE_BUTTON_TEXT) ?: ""

        negativeButton.setOnClickListener {
            onNegativeButtonClicked()
        }

        positiveButton.setOnClickListener {
            onPositiveButtonClicked()
        }

        return dialog
    }

    private fun onNegativeButtonClicked() {
        dismiss()
        eventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE))
    }

    private fun onPositiveButtonClicked() {
        dismiss()
        eventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
    }

}