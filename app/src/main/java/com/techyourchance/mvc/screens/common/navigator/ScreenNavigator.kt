package com.techyourchance.mvc.screens.common.navigator

import androidx.fragment.app.FragmentManager
import com.techyourchance.mvc.screens.common.controllers.FragmentFrameWrapper
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsFragment
import com.techyourchance.mvc.screens.questionslist.QuestionsListFragment

class ScreenNavigator(
        private val fragmentManager: FragmentManager,
        private val fragmentFrameWrapper: FragmentFrameWrapper
){

    fun toQuestionDetails(questionId: String) {
        val layoutId = fragmentFrameWrapper.getFragmentFrame().id
        val fragment = QuestionDetailsFragment.newInstance(questionId)
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(layoutId, fragment)
                .commit()
    }

    fun toQuestionsList() {
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val layoutId = fragmentFrameWrapper.getFragmentFrame().id
        val fragment = QuestionsListFragment.newInstance()
        fragmentManager
                .beginTransaction()
                .add(layoutId, fragment)
                .commit()
    }

}