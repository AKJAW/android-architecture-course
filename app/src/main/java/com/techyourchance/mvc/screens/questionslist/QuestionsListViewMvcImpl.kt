package com.techyourchance.mvc.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.ViewMvcFactory
import com.techyourchance.mvc.screens.common.view.drawer.BaseNavDrawerViewMvc
import com.techyourchance.mvc.screens.common.view.drawer.DrawerItem
import com.techyourchance.mvc.screens.common.view.toolbar.ToolbarViewMvc

class QuestionsListViewMvcImpl(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        viewMvcFactory: ViewMvcFactory
):
        BaseNavDrawerViewMvc<QuestionsListViewMvc.Listener>(
                R.layout.layout_questions_list,
                inflater,
                parent
        ),
        QuestionsRecyclerAdapter.OnQuestionClickListener,
        QuestionsListViewMvc {

    private val progress: ProgressBar = findViewById(R.id.progress)

    private val toolbar: Toolbar = findViewById(R.id.toolbar)
    private val toolbarMvc: ToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar)

    private val questionsAdapter = QuestionsRecyclerAdapter(this, viewMvcFactory)
    private val recyclerView: RecyclerView =
            findViewById<RecyclerView>(R.id.recycler_questions).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = questionsAdapter
            }

    init {
        toolbarMvc.setTitle(R.string.questions_list_toolbar_title)
        toolbar.addView(toolbarMvc.rootView)
    }

    override fun onDrawerItemClicked(drawerItem: DrawerItem) {
        getListeners().forEach { listener ->
            when(drawerItem) {
                DrawerItem.QUESTIONS -> listener.onDrawerQuestionsClicked()
            }
        }
    }

    override fun bindQuestions(questions: List<Question>) {
        questionsAdapter.bindQuestions(questions)
    }

    override fun onQuestionClicked(question: Question) {
        getListeners().forEach {
            it.onQuestionClicked(question)
        }
    }

    override fun showProgressIndication() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgressIndication() {
        progress.visibility = View.GONE
    }

}


