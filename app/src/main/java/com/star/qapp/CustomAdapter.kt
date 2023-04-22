package com.star.qapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    var optionsAvailable = listOf(
        "Linux","Bash","Windows","Docker","Devops"
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