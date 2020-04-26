package com.techyourchance.mvc.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc
import com.techyourchance.mvc.screens.common.ViewMvcFactory

class QuestionsListViewMvcImpl(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        viewMvcFactory: ViewMvcFactory
):
        BaseObservableViewMvc<QuestionsListViewMvc.Listener>(),
        QuestionsRecyclerAdapter.OnQuestionClickListener,
        QuestionsListViewMvc {

    override val rootView: View = inflater.inflate(R.layout.layout_questions_list, parent,false)

    private val questionsAdapter = QuestionsRecyclerAdapter(this, viewMvcFactory)
    private val recyclerView: RecyclerView =
            findViewById<RecyclerView>(R.id.recycler_questions).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = questionsAdapter
            }

    override fun bindQuestions(questions: List<Question>) {
        questionsAdapter.bindQuestions(questions)
    }

    override fun onQuestionClicked(question: Question) {
        getListeners().forEach {
            it.onQuestionClicked(question)
        }
    }

}
