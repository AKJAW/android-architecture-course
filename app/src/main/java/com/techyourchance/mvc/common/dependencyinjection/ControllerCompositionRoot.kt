package com.techyourchance.mvc.common.dependencyinjection

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.screens.common.MessageDisplayer
import com.techyourchance.mvc.screens.common.ScreenNavigator
import com.techyourchance.mvc.screens.common.ViewMvcFactory
import com.techyourchance.mvc.screens.questionslist.QuestionsListController

class ControllerCompositionRoot(compositionRoot: CompositionRoot, activity: Activity) {

    private val stackOverflowApi: StackoverflowApi by lazy {
        compositionRoot.stackOverflowApi
    }

    private val layoutInflater: LayoutInflater = LayoutInflater.from(activity)

    val viewMvcFactory = ViewMvcFactory(layoutInflater)

    private val context: Context = activity

    private val screensNavigator = ScreenNavigator(context)

    val messageDisplayer = MessageDisplayer(context)

    val fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase by lazy {
        FetchQuestionDetailsUseCase(stackOverflowApi)
    }

    val fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase by lazy {
        FetchLastActiveQuestionsUseCase(stackOverflowApi)
    }

    val questionsListController: QuestionsListController by lazy {
        QuestionsListController(
                screensNavigator,
                messageDisplayer,
                fetchLastActiveQuestionsUseCase
        )
    }
}
