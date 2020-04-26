package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import org.w3c.dom.Text

class QuestionsListAdapter(
        private val onQuestionClickListener: OnQuestionClickListener,
        context: Context
) : ArrayAdapter<Question>(context, 0) {

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    private class ViewHolder (val title: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
                ?: LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.layout_question_list_item, parent, false)
                        .apply {
                            val viewHolder = ViewHolder(findViewById(R.id.txt_title))
                            tag = viewHolder
                        }

        val question = getItem(position)

        val viewHolder = view.tag as ViewHolder
        // bind the data to views
        viewHolder.title.text = question!!.title

        // set listener
        view.setOnClickListener { onQuestionClicked(question) }
        return view
    }

    private fun onQuestionClicked(question: Question) {
        onQuestionClickListener.onQuestionClicked(question)
    }

}