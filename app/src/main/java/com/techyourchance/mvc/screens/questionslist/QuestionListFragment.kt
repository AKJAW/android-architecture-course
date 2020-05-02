package com.techyourchance.mvc.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.mvc.screens.common.controllers.BaseFragment
import com.techyourchance.mvc.screens.common.navigator.BackPressedListener

class QuestionListFragment: BaseFragment(), BackPressedListener {

    private lateinit var controller: QuestionsListController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewMvc = compositionRoot.viewMvcFactory.getQuestionsListViewMvc(null)

        controller = compositionRoot.questionsListController
        controller.bindView(viewMvc)

        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        controller.onStart()
    }

    override fun onStop() {
        super.onStop()
        controller.onStop()
    }

    override fun onBackPressed(): Boolean = controller.onBackPressed()
}