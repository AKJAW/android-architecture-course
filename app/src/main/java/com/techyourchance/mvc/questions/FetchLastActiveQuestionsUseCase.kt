package com.techyourchance.mvc.questions

import com.techyourchance.mvc.common.BaseObservable
import com.techyourchance.mvc.common.Constants
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.networking.questions.QuestionSchema
import com.techyourchance.mvc.networking.questions.QuestionsListResponseSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchLastActiveQuestionsUseCase(private val stackoverflowApi: StackoverflowApi)
    : BaseObservable<FetchLastActiveQuestionsUseCase.Listener>() {

    interface Listener {

        fun fetchLastActiveQuestionsSuccess(questions: List<Question>)

        fun fetchLastActiveQuestionsFailed()
    }

    fun fetchLastActiveQuestionsAndNotify(){
        stackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(object : Callback<QuestionsListResponseSchema> {
                    override fun onResponse(
                            call: Call<QuestionsListResponseSchema>,
                            response:
                            Response<QuestionsListResponseSchema>
                    ) {
                        val body = response.body()
                        if (response.isSuccessful && body != null) {
                            onSuccess(body.questions)
                        } else {
                            onFailed()
                        }
                    }

                    override fun onFailure(
                            call: Call<QuestionsListResponseSchema>,
                            t: Throwable
                    ) {
                        onFailed()
                    }
                })
    }

    private fun onSuccess(result: List<QuestionSchema>){
        val questions = result.map {
            Question(it.id, it.title)
        }

        getListeners().forEach {
            it.fetchLastActiveQuestionsSuccess(questions)
        }
    }

    private fun onFailed(){
        getListeners().forEach {
            it.fetchLastActiveQuestionsFailed()
        }
    }

}