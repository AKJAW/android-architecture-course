package com.techyourchance.mvc.screens.questionslist

import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.dialogs.DialogManager
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator

class QuestionsListController(
        private val screenNavigator: ScreenNavigator,
        private val dialogManager: DialogManager,
        private val fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase
):
        QuestionsListViewMvc.Listener,
        FetchLastActiveQuestionsUseCase.Listener {

    private lateinit var viewMvc: QuestionsListViewMvc

    fun bindView(view: QuestionsListViewMvc){
        viewMvc = view
    }

    fun onStart(){
        viewMvc.registerListener(this)
        viewMvc.showProgressIndication()

        fetchLastActiveQuestionsUseCase.registerListener(this)
        fetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    fun onStop(){
        viewMvc.unregisterListener(this)

        fetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        screenNavigator.toQuestionDetails(question.id)
    }

    override fun fetchLastActiveQuestionsSuccess(questions: List<Question>) {
        viewMvc.hideProgressIndication()
        viewMvc.bindQuestions(questions)
    }

    override fun fetchLastActiveQuestionsFailed() {
        viewMvc.hideProgressIndication()
        dialogManager.showUseCaseErrorDialog()
    }
}