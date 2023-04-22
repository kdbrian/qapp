package com.star.qapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class filtercontent : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtercontent)

        recyclerView = findViewById(R.id.recyclerview_contentfilters)


//        making the layout staggered
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        var adapter : CustomAdapter = CustomAdapter()

        recyclerView.adapter =adapter
    }
}