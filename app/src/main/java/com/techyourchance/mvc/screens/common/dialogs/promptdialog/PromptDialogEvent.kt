package com.techyourchance.mvc.screens.common.dialogs.promptdialog

class PromptDialogEvent(val buttonClicked: Button) {

    enum class Button {
        POSITIVE,
        NEGATIVE
    }
}