package com.techyourchance.mvc.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.mvc.screens.common.dialogs.promptdialog.PromtViewMvc
import com.techyourchance.mvc.screens.common.dialogs.promptdialog.PromtViewMvcImpl
import com.techyourchance.mvc.screens.common.view.drawer.NavDrawerHelper
import com.techyourchance.mvc.screens.common.view.drawer.NavDrawerViewMvc
import com.techyourchance.mvc.screens.common.view.drawer.NavDrawerViewMvcImpl
import com.techyourchance.mvc.screens.common.view.toolbar.ToolbarViewMvc
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsViewMvc
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvc
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvcImpl
import com.techyourchance.mvc.screens.questionslist.item.QuestionsListItemViewMvc
import com.techyourchance.mvc.screens.questionslist.item.QuestionsListItemViewMvcImpl

class ViewMvcFactory(
        private val layoutInflater: LayoutInflater,
        private val navDrawerHelper: NavDrawerHelper
){

    fun getQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvcImpl(navDrawerHelper, layoutInflater, parent, this)
    }

    fun getQuestionsListItemViewMvc(parent: ViewGroup?): QuestionsListItemViewMvc {
        return QuestionsListItemViewMvcImpl(layoutInflater, parent)
    }

    fun getQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsViewMvc {
        return QuestionDetailsViewMvcImpl(layoutInflater, parent, this)
    }

    fun getToolbarViewMvc(parent: ViewGroup?): ToolbarViewMvc {
        return ToolbarViewMvc(layoutInflater, parent)
    }

    fun getNavDrawerViewMvc(parent: ViewGroup?): NavDrawerViewMvc {
        return NavDrawerViewMvcImpl(layoutInflater, parent)
    }

    fun getPromptViewMvc(parent: ViewGroup?): PromtViewMvc {
        return PromtViewMvcImpl(layoutInflater, parent)
    }

}