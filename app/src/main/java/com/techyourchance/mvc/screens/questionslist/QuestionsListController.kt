package com.techyourchance.mvc.screens.questionslist

import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.MessageDisplayer
import com.techyourchance.mvc.screens.common.ScreenNavigator

class QuestionsListController(
        private val screenNavigator: ScreenNavigator,
        private val messageDisplayer: MessageDisplayer,
        private val fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase
): QuestionsListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener {

    private lateinit var viewMvc: QuestionsListViewMvc

    fun bindView(view: QuestionsListViewMvc){
        viewMvc = view
        viewMvc.registerListener(this)
    }

    fun onStart(){
        fetchLastActiveQuestionsUseCase.registerListener(this)
        fetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    fun onStop(){
        fetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        screenNavigator.toQuestionDetails(question.id)
    }

    override fun fetchLastActiveQuestionsSuccess(questions: List<Question>) {
        bindQuestions(questions)
    }

    private fun bindQuestions(questions: List<Question>) {
        viewMvc.bindQuestions(questions)
    }

    override fun fetchLastActiveQuestionsFailed() {
        messageDisplayer.showToast(R.string.error_network_call_failed)
    }
}