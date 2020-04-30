package com.techyourchance.mvc.screens.common.navigator

import android.content.Context
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity

class ScreenNavigator(private val context: Context){

    fun toQuestionDetails(questionId: String) {
        QuestionDetailsActivity.start(context, questionId)
    }

}