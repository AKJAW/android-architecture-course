package com.techyourchance.mvc.common.dependencyinjection

import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.mvc.networking.StackoverflowApi
import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.screens.common.ViewMvcFactory
import com.techyourchance.mvc.screens.common.controllers.BackPressedDispatcher
import com.techyourchance.mvc.screens.common.fragmenthelper.FragmentContainerWrapper
import com.techyourchance.mvc.screens.common.fragmenthelper.FragmentHelper
import com.techyourchance.mvc.screens.common.main.MainActivity
import com.techyourchance.mvc.screens.common.messages.ToastHelper
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator
import com.techyourchance.mvc.screens.questionslist.QuestionsListController

class ControllerCompositionRoot(
        private val activity: FragmentActivity,
        compositionRoot: CompositionRoot
) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(activity)

    val viewMvcFactory = ViewMvcFactory(layoutInflater)

    private val context: Context = activity

    val backPressedDispatcher: BackPressedDispatcher = activity as MainActivity

    private val fragmentFrameWrapper: FragmentContainerWrapper = activity as MainActivity

    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    private val fragmentHelper: FragmentHelper = FragmentHelper(activity, fragmentFrameWrapper, fragmentManager)

    val screenNavigator = ScreenNavigator(fragmentHelper)

    val toastHelper = ToastHelper(context)

    private val stackOverflowApi: StackoverflowApi by lazy {
        compositionRoot.stackOverflowApi
    }

    val fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase by lazy {
        FetchQuestionDetailsUseCase(stackOverflowApi)
    }

    val fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase by lazy {
        FetchLastActiveQuestionsUseCase(stackOverflowApi)
    }

    val questionsListController: QuestionsListController by lazy {
        QuestionsListController(
                backPressedDispatcher,
                screenNavigator,
                toastHelper,
                fetchLastActiveQuestionsUseCase
        )
    }

}
