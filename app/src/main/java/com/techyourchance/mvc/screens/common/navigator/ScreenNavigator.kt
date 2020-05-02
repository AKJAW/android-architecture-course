package com.techyourchance.mvc.screens.common.navigator

import com.techyourchance.mvc.screens.common.fragmenthelper.FragmentHelper
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsFragment
import com.techyourchance.mvc.screens.questionslist.QuestionsListFragment

class ScreenNavigator(
        private val fragmentHelper: FragmentHelper
){

    fun toQuestionDetails(questionId: String) {
        fragmentHelper.replaceFragment(QuestionDetailsFragment.newInstance(questionId))
    }

    fun toQuestionsList() {
        fragmentHelper.replaceFragmentAndClearHistory(QuestionsListFragment.newInstance())
    }

    fun navigateUp() {
        fragmentHelper.navigateUp()
    }

}