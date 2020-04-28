package com.techyourchance.mvc.common.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.screens.common.ViewMvcFactory

class ControllerCompositionRoot(compositionRoot: CompositionRoot, activity: Activity) {

    val stackOverflowApi: StackoverflowApi by lazy {
        compositionRoot.stackOverflowApi
    }

    private val layoutInflater: LayoutInflater = LayoutInflater.from(activity)

    val viewMvcFactory = ViewMvcFactory(layoutInflater)

    val fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase by lazy {
        FetchQuestionDetailsUseCase(stackOverflowApi)
    }

}