package com.techyourchance.mvc.screens.questionslist

import android.view.View
import com.techyourchance.mvc.questions.Question

interface QuestionsListItemViewMvc {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    val rootView: View

    fun registerListener(listener: Listener)

    fun unregisterListener(listener: Listener)

    fun bindQuestion(question: Question)
}