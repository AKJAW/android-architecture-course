package com.techyourchance.mvc.screens.questionslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.ViewMvcFactory

class QuestionsRecyclerAdapter(
        private val onQuestionClickListener: OnQuestionClickListener,
        private val viewMvcFactory: ViewMvcFactory
) : RecyclerView.Adapter<QuestionsRecyclerAdapter.MyViewHolder>(), QuestionsListItemViewMvc.Listener {

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    inner class MyViewHolder(val viewMvc: QuestionsListItemViewMvc)
        : RecyclerView.ViewHolder(viewMvc.rootView)

    private var questions = listOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewMvc = viewMvcFactory.getQuestionsListItemViewMvc(parent)

        viewMvc.registerListener(this)

        return MyViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val question = questions.getOrNull(position) ?: return

        holder.viewMvc.bindQuestion(question)
    }

    override fun getItemCount(): Int = questions.size

    override fun onQuestionClicked(question: Question) {
        onQuestionClickListener.onQuestionClicked(question)
    }

    fun bindQuestions(questions: List<Question>){
        this.questions = questions.toList()
        notifyDataSetChanged()
    }

}