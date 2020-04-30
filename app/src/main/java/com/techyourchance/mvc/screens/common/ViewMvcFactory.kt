package com.techyourchance.mvc.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.mvc.screens.common.view.toolbar.ToolbarViewMvc
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsViewMvc
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvc
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvcImpl
import com.techyourchance.mvc.screens.questionslist.item.QuestionsListItemViewMvc
import com.techyourchance.mvc.screens.questionslist.item.QuestionsListItemViewMvcImpl

class ViewMvcFactory(private val layoutInflater: LayoutInflater){

    fun getQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvcImpl(layoutInflater, parent, this)
    }

    fun getQuestionsListItemViewMvc(parent: ViewGroup?): QuestionsListItemViewMvc {
        return QuestionsListItemViewMvcImpl(layoutInflater, parent)
    }

    fun getQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsViewMvc {
        return QuestionDetailsViewMvcImpl(layoutInflater, parent)
    }

    fun getToolbarViewMvc(parent: ViewGroup?): ToolbarViewMvc {
        return ToolbarViewMvc(layoutInflater, parent)
    }

}