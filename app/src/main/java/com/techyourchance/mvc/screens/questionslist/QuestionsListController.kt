package com.techyourchance.mvc.screens.questionslist

import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.messages.ToastHelper
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator

class QuestionsListController(
        private val screenNavigator: ScreenNavigator,
        private val toastHelper: ToastHelper,
        private val fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase
): QuestionsListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener {

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

    //It's better to expose the view implementation to the controller
    //than the other way around, exposing the controller implementation to the view
    fun onBackPressed(): Boolean {
        if(viewMvc.isDrawerShown()){
            viewMvc.closeDrawer()
            return true
        }

        return false
    }

    override fun onQuestionClicked(question: Question) {
        screenNavigator.toQuestionDetails(question.id)
    }

    override fun onDrawerQuestionsClicked() { /* empty because you're already on the screen */ }

    override fun fetchLastActiveQuestionsSuccess(questions: List<Question>) {
        viewMvc.hideProgressIndication()
        viewMvc.bindQuestions(questions)
    }

    override fun fetchLastActiveQuestionsFailed() {
        viewMvc.hideProgressIndication()
        toastHelper.show(R.string.error_network_call_failed)
    }
}