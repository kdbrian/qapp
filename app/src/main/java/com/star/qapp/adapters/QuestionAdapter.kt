package com.star.qapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.star.qapp.R
import com.star.qapp.model.Answers

class QuestionAdapter(val questionAnswers : List<Answers>?, var context: Context) : RecyclerView.Adapter<QuestionAdapter.CustViewHolder3>(){

    class CustViewHolder3 (itemview : View): RecyclerView.ViewHolder(itemview) {

        val mqn_text : TextView
        val questionsTags: RecyclerView
        val questionsChoices: RecyclerView

        init {
            mqn_text=itemview.findViewById(R.id.qn_text)
            questionsTags=itemview.findViewById(R.id.question_tags)
            questionsChoices=itemview.findViewById(R.id.question_choices)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustViewHolder3 {
        Log.d("QAdapter","Oncreate vh called")
        //inflating the questions
        val view :View = LayoutInflater.from(parent.context).inflate(R.layout.question_view, parent, false)
        return CustViewHolder3(view)
    }

    override fun getItemCount(): Int {
        return questionAnswers?.size ?: 1
    }

    override fun onBindViewHolder(holder: CustViewHolder3, position: Int) {
        fillTags(listOf("devops","nodejs"), holder, context)
        fillChoices(listOf("Choice 1", "Choice 2", "Choice 2"), holder, context)

        holder.mqn_text.setText("Hey there ?\nThis is a example of a question you are\n likely to expect")
    }

//    fill the tags of this question
    fun fillTags(tags : List<String>, custViewHolder3: CustViewHolder3, context: Context){
        custViewHolder3.questionsTags.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        custViewHolder3.questionsTags.adapter = QuestionTagAdapter(tags)//TODO:pass tags
    }

//    fill the choices of this question
    fun fillChoices(choices: List<String>,custViewHolder3: CustViewHolder3, context: Context){
        custViewHolder3.questionsChoices.layoutManager = GridLayoutManager(context, 1,GridLayoutManager.VERTICAL, false)
        custViewHolder3.questionsChoices.adapter = QuestionChoicesAdapter(choices)
    }

}