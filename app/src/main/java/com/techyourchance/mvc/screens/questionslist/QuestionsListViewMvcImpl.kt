package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question

class QuestionsListViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?)
    : QuestionsRecyclerAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    override val rootView: View = inflater.inflate(R.layout.layout_questions_list, parent,false)
    private val context: Context
        get() = rootView.context

    private val listeners = mutableListOf<QuestionsListViewMvc.Listener>()

    private val questionsRecyclerAdapter = QuestionsRecyclerAdapter(inflater, this)
    private val questionsRecyclerView: RecyclerView =
            findViewById<RecyclerView>(R.id.recycler_questions).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = questionsRecyclerAdapter
            }


    private fun <T: View> findViewById(id: Int): T = rootView.findViewById(id)

    override fun registerListener(listener: QuestionsListViewMvc.Listener){
        listeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListViewMvc.Listener){
        listeners.remove(listener)
    }

    override fun bindQuestions(questions: List<Question>) {
        questionsRecyclerAdapter.bindQuestions(questions)
    }

    override fun onQuestionClicked(question: Question) {
        listeners.forEach {
            it.onQuestionClicked(question)
        }
    }

}
