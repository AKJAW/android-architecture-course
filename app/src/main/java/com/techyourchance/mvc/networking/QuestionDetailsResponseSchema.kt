package com.techyourchance.mvc.networking

import com.google.gson.annotations.SerializedName
import java.util.*


class QuestionDetailsResponseSchema(question: QuestionSchema) {

    @SerializedName("items")
    private val mQuestions: List<QuestionSchema> = Collections.singletonList(question)

    val question: QuestionSchema
        get() = mQuestions[0]

}