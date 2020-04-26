package com.techyourchance.mvc.screens.questionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techyourchance.mvc.questions.Question

class QuestionsRecyclerAdapter(
        private val inflater: LayoutInflater,
        private val onQuestionClickListener: OnQuestionClickListener
) : RecyclerView.Adapter<QuestionsRecyclerAdapter.MyViewHolder>(), QuestionsListItemViewMvc.Listener {

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    inner class MyViewHolder(val viewMvc: QuestionsListItemViewMvc)
        : RecyclerView.ViewHolder(viewMvc.rootView)

    private var questions = listOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewMvc = QuestionsListItemViewMvcImpl(inflater, parent)

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