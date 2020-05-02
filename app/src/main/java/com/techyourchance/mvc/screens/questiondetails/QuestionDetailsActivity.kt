package com.techyourchance.mvc.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.techyourchance.mvc.R
import com.techyourchance.mvc.screens.common.controllers.BaseActivity
import com.techyourchance.mvc.screens.common.navigator.BackPressedListener

class QuestionDetailsActivity : BaseActivity() {
    companion object {
        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    private lateinit var backPressedListener: BackPressedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_fragment_placeholder)

        val fragment: QuestionsDetailsFragment
        if(savedInstanceState == null){
            val questionId = intent.getStringExtra(EXTRA_QUESTION_ID) ?: ""
            fragment = QuestionsDetailsFragment.newInstance(questionId)
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.placeholder_frame_layout, fragment).commit()
        } else {
            fragment = supportFragmentManager
                    .findFragmentById(R.id.placeholder_frame_layout) as QuestionsDetailsFragment
        }
        backPressedListener = fragment
    }

    override fun onBackPressed() {
        if(!backPressedListener.onBackPressed()){
            super.onBackPressed()
        }
    }
}