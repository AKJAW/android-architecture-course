package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question

class QuestionsListViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?)
    : QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    override val rootView: View = inflater.inflate(R.layout.layout_questions_list, parent,false)
    private val context: Context
        get() = rootView.context

    private val questionsListView: ListView
    private val questionsListAdapter: QuestionsListAdapter

    private val listeners = mutableListOf<QuestionsListViewMvc.Listener>()

    init {
        questionsListView = findViewById(R.id.lst_questions)
        questionsListAdapter = QuestionsListAdapter(this, context)
        questionsListView.adapter = questionsListAdapter
    }

    private fun <T: View> findViewById(id: Int): T = rootView.findViewById(id)

    override fun registerListener(listener: QuestionsListViewMvc.Listener){
        listeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListViewMvc.Listener){
        listeners.remove(listener)
    }

    override fun bindQuestions(questions: List<Question>) {
        questionsListAdapter.clear()
        questionsListAdapter.addAll(questions)
        questionsListAdapter.notifyDataSetChanged()
    }

    override fun onQuestionClicked(question: Question) {
        listeners.forEach {
            it.onQuestionClicked(question)
        }
    }

}
