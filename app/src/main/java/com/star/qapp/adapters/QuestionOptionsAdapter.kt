package com.star.qapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.star.qapp.R

class QuestionOptionsAdapter : RecyclerView.Adapter<QuestionOptionsAdapter.CustomViewHolder>() {

    var optionsAvailable = listOf(
        "Linux","Bash","Windows","Docker","Devops","Networking","Programming",
        "PHP", "JS","Python","Cloud","Kubernetes"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.contentview, parent, false);
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return optionsAvailable.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.contentName.setText(optionsAvailable[position])
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


        val contentName: TextView

        init {
            contentName = itemView.findViewById(R.id.chckboxtxt)
        }

    }


}