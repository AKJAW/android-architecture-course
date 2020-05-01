package com.techyourchance.mvc.screens.questionslist

import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.view.ObservableViewMvc
import com.techyourchance.mvc.screens.common.view.drawer.NavDrawerViewMvc

interface QuestionsListViewMvc:
        ObservableViewMvc<QuestionsListViewMvc.Listener>,
        NavDrawerViewMvc {

    interface Listener {
        fun onQuestionClicked(question: Question)

        fun onDrawerQuestionsClicked()
    }

    fun bindQuestions(questions: List<Question>)

    fun showProgressIndication()

    fun hideProgressIndication()
}