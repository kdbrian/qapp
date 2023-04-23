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
import com.google.gson.Gson
import com.star.qapp.adapters.QuestionOptionsAdapter
import com.star.qapp.model.Answer
import com.star.qapp.model.Answers
import com.star.qapp.model.Tag
import okhttp3.*
import org.json.JSONArray
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
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
//                    use json to parse the response
                    var array = JSONArray(responseBody)


                    //model mapper init
                    var modelMapper = ModelMapper()
                    var answers = mutableListOf<Answer>()

//                    val answerType = object : TypeToken<List<Answer>>() {}.type
//                    val tagType = object : TypeToken<List<Tag>>() {}.type

                    for (i in 0 until array.length()) {
                        val item = array.getJSONObject(i)

//                        convert each object into its equivalent
                        Log.d("FetchQ","item $item")
                        answers.add(Answer(item))

                        Log.d("FetchQ", "$item")
                        Log.d("Item", "Id:${item.get("id")}")
//                        Log.d("Item", "C.Answers:${item.get("correct_answers")}")
//                        Log.d("Item", "C.Answers:${item.getJSONObject("correct_answers")}")
//                        Log.d("Item", "answer_a:${item.getJSONObject("correct_answers").getString("answer_a_correct")}")
//                        Log.d("Item", "answer_a:${item.optString("answer_a")}")
                    }

                    Log.d("FetchQ", " Answers : $answers")
//                    create a intent and pass the data with it
//                    intent.putExtra("Body", answersList.toString())

//                    Log.d("FetchQ", "${response.code} ${response.body}")
                    Toast.makeText(applicationContext, "$array", Toast.LENGTH_SHORT).show()

//                    Log.d("FetchQ", "Body $array")

//                    move to new activity
                    startActivity(intent)
                }

            }
        })
    }

}