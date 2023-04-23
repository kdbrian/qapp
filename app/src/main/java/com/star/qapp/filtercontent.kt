package com.star.qapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.star.qapp.adapters.QuestionOptionsAdapter
import com.star.qapp.model.Answer
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class filtercontent : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var generateBtn : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtercontent)

        recyclerView = findViewById(R.id.recyclerview_contentfilters)

        generateBtn = findViewById(R.id.generateQnsBtn)

        generateBtn.setOnClickListener { view: View ->
            var intent : Intent = Intent(this@filtercontent, questions::class.java)
            fetchQuestions("https://quizapi3.p.rapidapi.com/api/v1/questions?apiKey=A35sVGBzwlwcWYvbPo4HTrNugsjIlI5BZUfn7QBD&tags=Linux&limit=3", intent)
        }

//        making the layout staggered
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        var adapter : QuestionOptionsAdapter = QuestionOptionsAdapter()

        recyclerView.adapter =adapter
    }
    //    send okhttp request
    fun fetchQuestions(url : String, intent: Intent) {
        Log.d("FetchQ", "$url")
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                print("Failed to fetch data")
                throw e
            }

            override fun onResponse(call: Call, response: Response) {

                runOnUiThread{
                    val responseBody = response.body?.string()
                    intent.putExtra("response", responseBody)
                    startActivity(intent)
                }

            }
        })
    }

}