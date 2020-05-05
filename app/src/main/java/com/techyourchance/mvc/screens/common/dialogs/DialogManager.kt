package com.techyourchance.mvc.screens.common.dialogs

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.techyourchance.mvc.R
import com.techyourchance.mvc.screens.common.dialogs.promptdialog.PromptDialog

class DialogManager(
        private val context: Context,
        private val fragmentManager: FragmentManager
) {

    fun showUseCaseErrorDialog(tag: String?){
        val dialog: DialogFragment = PromptDialog.newPromptDialog(
                title = getString(R.string.error_network_call_failed_title),
                message = getString(R.string.error_network_call_failed_message),
                positiveButtonText = getString(R.string.error_network_call_failed_positive_button),
                negativeButtonText = getString(R.string.error_network_call_failed_negative_button)
        )

        dialog.show(fragmentManager, tag)
    }

    private fun getString(@StringRes id: Int): String = context.getString(id)

    fun getShownDialogTag(): String? {
        return fragmentManager.fragments.firstOrNull { it is BaseDialog }?.tag
    }
}