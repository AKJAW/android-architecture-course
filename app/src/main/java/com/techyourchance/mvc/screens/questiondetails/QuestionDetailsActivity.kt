package com.techyourchance.mvc.screens.questiondetails

import android.content.Context
import android.content.Intent

import com.techyourchance.mvc.screens.common.BaseActivity


class QuestionDetailsActivity : BaseActivity() {

    companion object {
        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId: String?) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }
}