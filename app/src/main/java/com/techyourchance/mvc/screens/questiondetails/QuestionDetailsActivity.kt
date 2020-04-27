package com.techyourchance.mvc.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.techyourchance.mvc.R
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema
import com.techyourchance.mvc.networking.QuestionSchema
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionDetailsActivity : BaseActivity() {

    companion object {
        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId: String?) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    private lateinit var stackoverflowApi: StackoverflowApi
    private lateinit var viewMvc: QuestionDetailsViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stackoverflowApi = compositionRoot.stackOverflowApi
        viewMvc = compositionRoot.viewMvcFactory.getQuestionDetailsViewMvc(null)

        setContentView(viewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()

        viewMvc.showProgressIndicator()
        fetchQuestionDetails()
    }

    private fun fetchQuestionDetails() {
        stackoverflowApi.fetchQuestionDetails(getQuestionId())
                .enqueue(object : Callback<QuestionDetailsResponseSchema?> {
                    override fun onResponse(
                            call: Call<QuestionDetailsResponseSchema?>?,
                            response: Response<QuestionDetailsResponseSchema?>
                    ) {
                        val body = response.body()
                        if (response.isSuccessful && body != null) {
                            bindQuestionDetails(body.question)
                        } else {
                            networkCallFailed()
                        }
                    }

                    override fun onFailure(
                            call: Call<QuestionDetailsResponseSchema?>?,
                            t: Throwable?
                    ) {
                        networkCallFailed()
                    }
                })
    }

    private fun getQuestionId(): String? {
        return intent.getStringExtra(EXTRA_QUESTION_ID)
    }

    private fun bindQuestionDetails(questionSchema: QuestionSchema) {
        viewMvc.hideProgressIndicator()
        viewMvc.bindQuestion(
                QuestionDetails(
                        questionSchema.id,
                        questionSchema.title,
                        questionSchema.body
                )
        )
    }

    private fun networkCallFailed() {
        viewMvc.hideProgressIndicator()
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }
}