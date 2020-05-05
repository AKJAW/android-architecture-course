package com.techyourchance.mvc.screens.questionslist

import android.os.Parcelable
import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.dialogs.DialogManager
import com.techyourchance.mvc.screens.common.dialogs.DialogsEventBus
import com.techyourchance.mvc.screens.common.dialogs.promptdialog.PromptDialogEvent
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator
import kotlinx.android.parcel.Parcelize

class QuestionsListController(
        private val screenNavigator: ScreenNavigator,
        private val dialogManager: DialogManager,
        private val dialogsEventBus: DialogsEventBus,
        private val fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase
):
        QuestionsListViewMvc.Listener,
        FetchLastActiveQuestionsUseCase.Listener,
        DialogsEventBus.Listener {

    internal enum class ScreenState {
        IDLE, FETCHING_QUESTIONS, QUESTIONS_LIST_SHOWN, NETWORK_ERROR
    }

    private lateinit var viewMvc: QuestionsListViewMvc
    private lateinit var screenState: ScreenState

    fun bindView(view: QuestionsListViewMvc){
        viewMvc = view
    }

    fun onStart(){
        viewMvc.registerListener(this)
        dialogsEventBus.registerListener(this)
        fetchLastActiveQuestionsUseCase.registerListener(this)

        if(screenState != ScreenState.NETWORK_ERROR){
            fetchQuestionsAndNotify()
            screenState = ScreenState.FETCHING_QUESTIONS
        }
    }

    fun onStop(){
        viewMvc.unregisterListener(this)
        dialogsEventBus.unregisterListener(this)
        fetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        screenNavigator.toQuestionDetails(question.id)
    }

    override fun fetchLastActiveQuestionsSuccess(questions: List<Question>) {
        screenState = ScreenState.QUESTIONS_LIST_SHOWN

        viewMvc.hideProgressIndication()
        viewMvc.bindQuestions(questions)
    }

    override fun fetchLastActiveQuestionsFailed() {
        screenState = ScreenState.NETWORK_ERROR

        viewMvc.hideProgressIndication()
        dialogManager.showUseCaseErrorDialog(null)
    }

    override fun onDialogEvent(event: Any) {
        if(event !is PromptDialogEvent) return

        screenState = when(event.buttonClicked){
            PromptDialogEvent.Button.POSITIVE -> {
                fetchQuestionsAndNotify()
                ScreenState.FETCHING_QUESTIONS
            }
            PromptDialogEvent.Button.NEGATIVE -> ScreenState.IDLE
        }
    }

    private fun fetchQuestionsAndNotify() {
        viewMvc.showProgressIndication()
        fetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    internal fun getSavedState(): SavedState = SavedState(screenState)

    internal fun restoreSavedState(savedState: SavedState?) {
        screenState = savedState?.screenState ?: ScreenState.IDLE
    }

    @Parcelize
    internal data class SavedState(val screenState: ScreenState): Parcelable
}