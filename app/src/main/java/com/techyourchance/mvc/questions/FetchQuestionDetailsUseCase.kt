package com.techyourchance.mvc.questions

import com.techyourchance.mvc.common.BaseObservable
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.networking.questions.QuestionDetailsResponseSchema
import com.techyourchance.mvc.networking.questions.QuestionSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchQuestionDetailsUseCase(private val stackoverflowApi: StackoverflowApi)
    : BaseObservable<FetchQuestionDetailsUseCase.Listener>() {

    interface Listener {

        fun fetchQuestionDetailsSuccess(details: QuestionDetails)

        fun fetchQuestionDetailsFailed()
    }

    fun fetchQuestionDetailsAndNotify(questionId: String){
        stackoverflowApi.fetchQuestionDetails(questionId)
                .enqueue(object : Callback<QuestionDetailsResponseSchema?> {
                    override fun onResponse(
                            call: Call<QuestionDetailsResponseSchema?>?,
                            response: Response<QuestionDetailsResponseSchema?>
                    ) {
                        val body = response.body()
                        if (response.isSuccessful && body != null) {
                            onSuccess(body.question)
                        } else {
                            onFailed()
                        }
                    }

                    override fun onFailure(
                            call: Call<QuestionDetailsResponseSchema?>?,
                            t: Throwable?
                    ) {
                        onFailed()
                    }
                })
    }

    private fun onSuccess(result: QuestionSchema){
        val details = QuestionDetails(
                result.id,
                result.title,
                result.body
        )
        getListeners().forEach {
            it.fetchQuestionDetailsSuccess(details)
        }
    }

    private fun onFailed(){
        getListeners().forEach {
            it.fetchQuestionDetailsFailed()
        }
    }

}