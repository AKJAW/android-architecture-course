package com.techyourchance.mvc.screens.questiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.controllers.BackPressedDispatcher
import com.techyourchance.mvc.screens.common.controllers.BaseFragment
import com.techyourchance.mvc.screens.common.dialogs.DialogManager
import com.techyourchance.mvc.screens.common.dialogs.DialogsEventBus
import com.techyourchance.mvc.screens.common.dialogs.promptdialog.PromptDialogEvent
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator

class QuestionDetailsFragment:
        BaseFragment(),
        FetchQuestionDetailsUseCase.Listener,
        QuestionDetailsViewMvc.Listener, DialogsEventBus.Listener {

    companion object {
        private const val SAVED_STATE_SCREEN_STATE = "SAVED_STATE_SCREEN_STATE"

        private const val TAG_PROMPT_DIALOG = "TAG_PROMPT_DIALOG"
        private const val ARG_QUESTION_ID = "ARG_QUESTION_ID"

        fun newInstance(questionId: String): QuestionDetailsFragment {
            val args = Bundle()
            args.putString(ARG_QUESTION_ID, questionId)
            val fragment = QuestionDetailsFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private enum class ScreenState {
        IDLE, QUESTION_DETAILS_SHOWN, NETWORK_ERROR
    }

    private lateinit var screenState: ScreenState

    private lateinit var viewMvc: QuestionDetailsViewMvc
    private lateinit var backPressedDispatcher: BackPressedDispatcher
    private lateinit var screenNavigator: ScreenNavigator
    private lateinit var dialogManager: DialogManager
    private lateinit var dialogsEventBus: DialogsEventBus

    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val savedScreenState = savedInstanceState?.getSerializable(SAVED_STATE_SCREEN_STATE) as? ScreenState
        screenState = savedScreenState ?: ScreenState.IDLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewMvc = compositionRoot.viewMvcFactory.getQuestionDetailsViewMvc(container)

        backPressedDispatcher = compositionRoot.backPressedDispatcher
        screenNavigator = compositionRoot.screenNavigator
        dialogManager = compositionRoot.dialogManager
        dialogsEventBus = compositionRoot.dialogEventBus

        fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase

        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()

        dialogsEventBus.registerListener(this)

        viewMvc.registerListener(this)
        viewMvc.showProgressIndicator()

        fetchQuestionDetailsUseCase.registerListener(this)
        val questionId = getQuestionId()
        if(questionId != null && screenState != ScreenState.NETWORK_ERROR){
            fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
        }
    }

    override fun onStop() {
        super.onStop()

        dialogsEventBus.unregisterListener(this)

        viewMvc.unregisterListener(this)

        fetchQuestionDetailsUseCase.unregisterListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(SAVED_STATE_SCREEN_STATE, screenState)
    }

    private fun getQuestionId(): String? {
        return arguments?.getString(ARG_QUESTION_ID)
    }

    override fun fetchQuestionDetailsSuccess(details: QuestionDetails) {
        screenState = ScreenState.QUESTION_DETAILS_SHOWN

        viewMvc.hideProgressIndicator()
        viewMvc.bindQuestion(details)
    }

    override fun fetchQuestionDetailsFailed() {
        screenState = ScreenState.NETWORK_ERROR

        viewMvc.hideProgressIndicator()
        dialogManager.showUseCaseErrorDialog(TAG_PROMPT_DIALOG)
    }

    override fun onBackButtonClicked() {
        screenNavigator.navigateUp()
    }

    override fun onDialogEvent(event: Any) {
        if (event !is PromptDialogEvent) return

        screenState = when(event.buttonClicked){
            PromptDialogEvent.Button.POSITIVE -> {
                val questionId = getQuestionId()
                if(questionId != null){
                    fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
                }
                ScreenState.IDLE

            }
            PromptDialogEvent.Button.NEGATIVE -> ScreenState.IDLE
        }
    }

}