package com.techyourchance.mvc.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc

class QuestionsListItemViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup):
        BaseObservableViewMvc<QuestionsListItemViewMvc.Listener>(),
        QuestionsListItemViewMvc {

    override val rootView: View = inflater.inflate(R.layout.layout_question_list_item, parent, false)

    private val title: TextView = findViewById(R.id.txt_title)

    private var question: Question? = null

    init {
        rootView.setOnClickListener {
            val question = this.question ?: return@setOnClickListener
            getListeners().forEach {
                it.onQuestionClicked(question)
            }
        }
    }

    override fun bindQuestion(question: Question) {
        this.question = question
        this.title.text = question.title
    }

}