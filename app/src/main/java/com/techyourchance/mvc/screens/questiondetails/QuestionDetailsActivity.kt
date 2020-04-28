package com.techyourchance.mvc.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.BaseActivity

class QuestionDetailsActivity : BaseActivity(), FetchQuestionDetailsUseCase.Listener {

    companion object {
        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId: String?) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
    private lateinit var viewMvc: QuestionDetailsViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase
        viewMvc = compositionRoot.viewMvcFactory.getQuestionDetailsViewMvc(null)

        setContentView(viewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()

        viewMvc.showProgressIndicator()

        val questionId = getQuestionId()
        if(questionId != null){
            fetchQuestionDetailsUseCase.registerListener(this)
            fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
        }
    }

    override fun onStop() {
        super.onStop()

        fetchQuestionDetailsUseCase.unregisterListener(this)
    }

    private fun getQuestionId(): String? {
        return intent.getStringExtra(EXTRA_QUESTION_ID)
    }

    override fun fetchQuestionDetailsSuccess(details: QuestionDetails) {
        bindQuestionDetails(details)
    }

    private fun bindQuestionDetails(questionDetails: QuestionDetails) {
        viewMvc.hideProgressIndicator()
        viewMvc.bindQuestion(questionDetails)
    }

    override fun fetchQuestionDetailsFailed() {
        viewMvc.hideProgressIndicator()
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }
}