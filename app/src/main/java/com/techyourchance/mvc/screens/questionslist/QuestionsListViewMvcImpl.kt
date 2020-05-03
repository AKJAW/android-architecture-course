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
import com.techyourchance.mvc.screens.common.view.BaseObservableViewMvc
import com.techyourchance.mvc.screens.common.view.drawer.NavDrawerHelper
import com.techyourchance.mvc.screens.common.view.toolbar.ToolbarViewMvc

class QuestionsListViewMvcImpl(
        private val navDrawerHelper: NavDrawerHelper,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        viewMvcFactory: ViewMvcFactory
):
        BaseObservableViewMvc<QuestionsListViewMvc.Listener>(),
        QuestionsRecyclerAdapter.OnQuestionClickListener,
        QuestionsListViewMvc {

    override val rootView: View = inflater.inflate(R.layout.layout_questions_list, parent, false)

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
        setUpToolbar()
    }

    private fun setUpToolbar() {
        toolbarMvc.setTitle(R.string.questions_list_toolbar_title)
        toolbarMvc.showHamburgerButton()
        toolbarMvc.setHamburgerButtonClickListener {
            navDrawerHelper.openDrawer()
        }
        toolbar.addView(toolbarMvc.rootView)
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


