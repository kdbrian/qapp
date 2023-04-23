package com.star.qapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.star.qapp.R

class QuestionChoicesAdapter (val choices : List<String>): RecyclerView.Adapter<QuestionChoicesAdapter.CustViewHolder2>() {

    class CustViewHolder2 (itemView: View) : RecyclerView.ViewHolder(itemView){

        val choiceContent : TextView

        init {
            choiceContent = itemView.findViewById(R.id.answer_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustViewHolder2 {

//        initialize the view and return it
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.choice_layout, parent, false)
        return CustViewHolder2(view)
    }

    override fun getItemCount(): Int {
        return choices.size
    }


    override fun onBindViewHolder(holder: CustViewHolder2, position: Int) {

//        set the values in the interface
        holder.choiceContent.setText(choices[position])
    }
}