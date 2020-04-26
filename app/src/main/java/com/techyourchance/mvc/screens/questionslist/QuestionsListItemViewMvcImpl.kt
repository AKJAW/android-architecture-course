package com.techyourchance.mvc.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question

class QuestionsListItemViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup): QuestionsListItemViewMvc {

    override val rootView: View = inflater.inflate(R.layout.layout_question_list_item, parent, false)
    private val title: TextView = findViewById(R.id.txt_title)

    private val listeners = mutableListOf<QuestionsListItemViewMvc.Listener>()

    private var question: Question? = null

    init {
        rootView.setOnClickListener {
            val question = this.question ?: return@setOnClickListener
            listeners.forEach {
                it.onQuestionClicked(question)
            }
        }
    }

    private fun <T: View> findViewById(id: Int): T = rootView.findViewById(id)

    override fun registerListener(listener: QuestionsListItemViewMvc.Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListItemViewMvc.Listener) {
        listeners.remove(listener)
    }

    override fun bindQuestion(question: Question) {
        this.question = question
        this.title.text = question.title
    }

}