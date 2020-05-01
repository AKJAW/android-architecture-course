package com.techyourchance.mvc.screens.questiondetails

import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.view.ObservableViewMvc
import com.techyourchance.mvc.screens.common.view.drawer.DrawerItem
import com.techyourchance.mvc.screens.common.view.drawer.NavDrawerViewMvc

interface QuestionDetailsViewMvc:
        ObservableViewMvc<QuestionDetailsViewMvc.Listener>,
        NavDrawerViewMvc {

    interface Listener {
        fun onBackButtonClicked()

        fun onDrawerItemClicked(drawerItem: DrawerItem)
    }

    fun bindQuestion(questionDetails: QuestionDetails)

    fun showProgressIndicator()

    fun hideProgressIndicator()

}