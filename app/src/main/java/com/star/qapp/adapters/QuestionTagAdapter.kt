package com.star.qapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.star.qapp.R
import com.star.qapp.model.Tag

class QuestionTagAdapter(val tags : List<Tag>) : RecyclerView.Adapter<QuestionTagAdapter.CustViewHolder>() {


    class CustViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val content: TextView

        init {
            content = itemView.findViewById(R.id.tagtext)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.tags, parent, false)
        return CustViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onBindViewHolder(holder: CustViewHolder, position: Int) {
//        set the fields
        holder.content.text = tags[position].name
    }
}