package com.techyourchance.mvc.screens.common.dialogs.promptdialog

import android.app.Dialog
import android.os.Bundle
import com.techyourchance.mvc.screens.common.dialogs.BaseDialog
import com.techyourchance.mvc.screens.common.dialogs.DialogsEventBus

class PromptDialog: BaseDialog(), PromtViewMvc.Listener {

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

    private lateinit var viewMvc: PromtViewMvc
    private lateinit var eventBus: DialogsEventBus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = compositionRoot.viewMvcFactory.getPromptViewMvc(null)
        eventBus = compositionRoot.dialogEventBus
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arguments = arguments
        if(arguments == null || arguments.isEmpty){
            throw IllegalArgumentException("InfoDialog arguments cannot be empty")
        }


        viewMvc.setTitle(arguments.getString(ARG_TITLE) ?: "")
        viewMvc.setMessage(arguments.getString(ARG_MESSAGE) ?: "")
        viewMvc.setNegativeButtonText(arguments.getString(ARG_NEGATIVE_BUTTON_TEXT) ?: "")
        viewMvc.setPositiveButtonText(arguments.getString(ARG_POSITIVE_BUTTON_TEXT) ?: "")

        val dialog = Dialog(requireContext())
        dialog.setContentView(viewMvc.rootView)

        return dialog
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onNegativeButtonClicked() {
        dismiss()
        eventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE))
    }

    override fun onPositiveButtonClicked() {
        dismiss()
        eventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
    }

}