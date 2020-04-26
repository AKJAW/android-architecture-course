package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.techyourchance.mvc.questions.Question

class QuestionsListAdapter(
        private val onQuestionClickListener: OnQuestionClickListener,
        context: Context
) : ArrayAdapter<Question>(context, 0), QuestionsListItemViewMvc.Listener {

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: createItemView(parent)

        val question = getItem(position)

        if(question != null){
            val viewMvc = view.tag as QuestionsListItemViewMvc

            viewMvc.bindQuestion(question)
        }

        return view
    }

    private fun createItemView(parent: ViewGroup): View {
        val viewMvc = QuestionsListItemViewMvcImpl(LayoutInflater.from(context), parent)

        viewMvc.registerListener(this)

        val view = viewMvc.rootView
        view.tag = viewMvc

        return view
    }

    override fun onQuestionClicked(question: Question) {
        onQuestionClickListener.onQuestionClicked(question)
    }

}