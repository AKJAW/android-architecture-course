package com.techyourchance.mvc.screens.questiondetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.ViewMvcFactory
import com.techyourchance.mvc.screens.common.view.drawer.BaseNavDrawerViewMvc
import com.techyourchance.mvc.screens.common.view.drawer.DrawerItem
import com.techyourchance.mvc.screens.common.view.toolbar.ToolbarViewMvc

class QuestionDetailsViewMvcImpl(
        layoutInflater: LayoutInflater,
        parent: ViewGroup?,
        viewMvcFactory: ViewMvcFactory
) :
        BaseNavDrawerViewMvc<QuestionDetailsViewMvc.Listener>(
                R.layout.layout_question_details,
                layoutInflater,
                parent
        ),
        QuestionDetailsViewMvc {

    private val toolbar: Toolbar = findViewById(R.id.toolbar)
    private val toolbarMvcView: ToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar)

    private val progress: ProgressBar = findViewById(R.id.progress)
    private val title: TextView = findViewById(R.id.txt_question_title)
    private val body: TextView = findViewById(R.id.txt_question_body)

    init {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        toolbarMvcView.setTitle(R.string.question_details_toolbar_title)
        toolbarMvcView.showBackButton()
        toolbarMvcView.setBackButtonClickListener {
            getListeners().forEach {
                it.onBackButtonClicked()
            }
        }

        toolbar.addView(toolbarMvcView.rootView)
    }

    override fun onDrawerItemClicked(drawerItem: DrawerItem) {
        getListeners().forEach {
            it.onDrawerItemClicked(drawerItem)
        }

    }

    override fun bindQuestion(questionDetails: QuestionDetails) {
        title.text = questionDetails.title
        body.text = questionDetails.body
    }

    override fun showProgressIndicator() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        progress.visibility = View.GONE
    }

}