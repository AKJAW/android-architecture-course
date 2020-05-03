package com.techyourchance.mvc.screens.questiondetails

import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.view.ObservableViewMvc

interface QuestionDetailsViewMvc:
        ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    interface Listener {
        fun onBackButtonClicked()
    }

    fun bindQuestion(questionDetails: QuestionDetails)

    fun showProgressIndicator()

    fun hideProgressIndicator()

}