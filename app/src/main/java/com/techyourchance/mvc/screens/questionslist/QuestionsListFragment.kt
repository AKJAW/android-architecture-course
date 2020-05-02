package com.techyourchance.mvc.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.mvc.screens.common.controllers.BaseFragment

class QuestionsListFragment: BaseFragment() {

    companion object {
        fun newInstance() = QuestionsListFragment()
    }

    private lateinit var controller: QuestionsListController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewMvc = compositionRoot.viewMvcFactory.getQuestionsListViewMvc(container)

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

}