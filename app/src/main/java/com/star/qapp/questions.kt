package com.star.qapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.star.qapp.adapters.QuestionAdapter
import com.star.qapp.model.Answer
import com.star.qapp.model.QuestionAnswersChoices
import org.json.JSONArray

class questions : AppCompatActivity() {

    private lateinit var questionScroll: RecyclerView
    private lateinit var mscorevalue: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)


        questionScroll = findViewById(R.id.questions_scroll)
        mscorevalue = findViewById(R.id.scorevalue)
//        inflate the questions using the question layout
        mscorevalue.setText("100")

        questionScroll.layoutManager = GridLayoutManager(this@questions, 1)

        if (intent.getStringExtra("response") != null) {
//
            var stringresponse = intent.getStringExtra("response")

//            Toast.makeText(this, "$stringresponse", Toast.LENGTH_SHORT).show()

            var array = JSONArray(stringresponse)
            //model mapper init
            var answers = mutableListOf<Answer>()
//                    filling the array list
            for (i in 0 until array.length()) {
                val item = array.getJSONObject(i)

//                        convert each object into its equivalent
//                Log.d("FetchQ", "item $item")
                answers.add(Answer(item))

            }
            Log.d("All", "$answers")

//            load espective adapters
            questionScroll.adapter = QuestionAdapter(answers, this)
        }
    }
}