package com.techyourchance.mvc.screens.questionslist

import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.ObservableViewMvc

interface QuestionsListViewMvc: ObservableViewMvc<QuestionsListViewMvc.Listener> {

    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestions(questions: List<Question>)

}