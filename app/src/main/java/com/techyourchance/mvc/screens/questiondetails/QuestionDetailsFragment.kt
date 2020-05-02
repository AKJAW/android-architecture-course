package com.techyourchance.mvc.screens.questiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.controllers.BaseFragment
import com.techyourchance.mvc.screens.common.messages.ToastHelper
import com.techyourchance.mvc.screens.common.navigator.BackPressedListener
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator
import com.techyourchance.mvc.screens.common.view.drawer.DrawerItem

class QuestionsDetailsFragment:
        BaseFragment(),
        FetchQuestionDetailsUseCase.Listener,
        QuestionDetailsViewMvc.Listener,
        BackPressedListener
{

    companion object {
        private const val ARG_QUESTION_ID = "ARG_QUESTION_ID"

        fun newInstance(questionId: String): QuestionsDetailsFragment {
            val args = Bundle()
            args.putString(ARG_QUESTION_ID, questionId)
            val fragment = QuestionsDetailsFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var viewMvc: QuestionDetailsViewMvc
    private lateinit var screenNavigator: ScreenNavigator
    private lateinit var toastHelper: ToastHelper
    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewMvc = compositionRoot.viewMvcFactory.getQuestionDetailsViewMvc(container)

        screenNavigator = compositionRoot.screenNavigator
        toastHelper = compositionRoot.messageDisplayer
        fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase

        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()

        viewMvc.registerListener(this)

        viewMvc.showProgressIndicator()

        val questionId = getQuestionId()
        if(questionId != null){
            fetchQuestionDetailsUseCase.registerListener(this)
            fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
        }
    }

    override fun onStop() {
        super.onStop()

        fetchQuestionDetailsUseCase.unregisterListener(this)
    }

    private fun getQuestionId(): String? {
        return arguments?.getString(ARG_QUESTION_ID)
    }

    override fun fetchQuestionDetailsSuccess(details: QuestionDetails) {
        bindQuestionDetails(details)
    }

    private fun bindQuestionDetails(questionDetails: QuestionDetails) {
        viewMvc.hideProgressIndicator()
        viewMvc.bindQuestion(questionDetails)
    }

    override fun fetchQuestionDetailsFailed() {
        viewMvc.hideProgressIndicator()
        toastHelper.show(R.string.error_network_call_failed)
    }

    override fun onBackButtonClicked() {
        onBackPressed()
    }

    override fun onDrawerItemClicked(drawerItem: DrawerItem) {
        when(drawerItem){
            DrawerItem.QUESTIONS -> screenNavigator.toQuestionsListClearTop()
        }
    }

    override fun onBackPressed(): Boolean {
        if(viewMvc.isDrawerShown()){
            viewMvc.closeDrawer()
            return true
        }
        return false
    }
}