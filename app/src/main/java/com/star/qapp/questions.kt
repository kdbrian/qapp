package com.star.qapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.star.qapp.adapters.QuestionAdapter

class questions : AppCompatActivity() {

    private lateinit var questionScroll:RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

//        receive the filter info from the prev page nd use it to send the request

//        from the response use a DTO to transform the fields

        questionScroll = findViewById(R.id.questions_scroll)
//        inflate the questions using the question layout

        questionScroll .layoutManager = GridLayoutManager (this@questions, 1)
        questionScroll.adapter = QuestionAdapter(null, this@questions)//TODO:change the null value
    }
}