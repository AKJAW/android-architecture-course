package com.techyourchance.mvc.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase
import com.techyourchance.mvc.questions.QuestionDetails
import com.techyourchance.mvc.screens.common.controllers.BaseActivity
import com.techyourchance.mvc.screens.common.messages.ToastHelper
import com.techyourchance.mvc.screens.common.navigator.ScreenNavigator
import com.techyourchance.mvc.screens.common.view.drawer.DrawerItem

class QuestionDetailsActivity : BaseActivity(), FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener {

    companion object {
        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    private lateinit var viewMvc: QuestionDetailsViewMvc
    private lateinit var screenNavigator: ScreenNavigator
    private lateinit var toastHelper: ToastHelper
    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = compositionRoot.viewMvcFactory.getQuestionDetailsViewMvc(null)

        screenNavigator = compositionRoot.screenNavigator

        toastHelper = compositionRoot.messageDisplayer

        fetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase

        setContentView(viewMvc.rootView)
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
        return intent.getStringExtra(EXTRA_QUESTION_ID)
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

    override fun onBackPressed() {
        if(viewMvc.isDrawerShown()){
            viewMvc.closeDrawer()
        } else {
            super.onBackPressed()
        }
    }
}