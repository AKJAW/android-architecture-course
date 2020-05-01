package com.techyourchance.mvc.screens.common.navigator

import android.content.Context
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.mvc.screens.questionslist.QuestionsListActivity

class ScreenNavigator(private val context: Context){

    fun toQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(context, questionId)
    }

    fun toQuestionsListClearTop() {
        QuestionsListActivity.startClearTop(context)
    }

}