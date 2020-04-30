package com.techyourchance.mvc.screens.questionslist

import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.view.ObservableViewMvc

interface QuestionsListViewMvc: ObservableViewMvc<QuestionsListViewMvc.Listener> {

    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestions(questions: List<Question>)

    //can these methods be in a stand alone interface?
    fun showProgressIndication()

    fun hideProgressIndication()
}