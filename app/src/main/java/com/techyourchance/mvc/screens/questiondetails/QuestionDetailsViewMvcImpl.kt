package com.techyourchance.mvc.screens.questiondetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.BaseViewMvc

class QuestionDetailsViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?)
    : BaseViewMvc(), QuestionDetailsViewMvc {

    override val rootView: View = layoutInflater.inflate(R.layout.layout_question_details, parent, false)

    private val progres: ProgressBar = rootView.findViewById(R.id.progress)
    private val title: TextView = rootView.findViewById(R.id.txt_question_title)
    private val body: TextView = rootView.findViewById(R.id.txt_question_body)


    override fun bindQuestion(questionDetails: QuestionDetails) {
        title.text = questionDetails.title
        body.text = questionDetails.body
    }

    override fun showProgressIndicator() {
        progres.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        progres.visibility = View.GONE
    }
}