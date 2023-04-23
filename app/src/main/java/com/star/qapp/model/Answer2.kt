package com.star.qapp.model

import org.json.JSONArray
import org.json.JSONObject

data class Answer @JvmOverloads constructor(
    val id: Int = 0,
    val question: String = "",
    val description: String? = null,
    val answers: QuestionAnswersChoices = QuestionAnswersChoices(),
    val multiple_correct_answers: Boolean = false,
    val correct_answers: CorrectAnswers? = null,
    val correct_answer: String? = null,
    val explanation: String? = null,
    val tip: String? = null,
    val tags: List<Tag> = emptyList(),
    val category: String = "",
    val difficulty: String = ""
) : java.io.Serializable{
    // Secondary constructor to create Question object from a JSON string
    constructor(json: JSONObject) : this(
        id = json.getInt("id"),
        question = json.getString("question"),
        description = json.optString("description", null),
        answers = QuestionAnswersChoices(
            answer_a = (json).getJSONObject("answers").getString("answer_a"),
            answer_b = (json).getJSONObject("answers").getString("answer_b"),
            answer_c = (json).getJSONObject("answers").getString("answer_c"),
            answer_d = (json).getJSONObject("answers").getString("answer_d"),
            answer_e = (json).getJSONObject("answers").getString("answer_e"),
            answer_f = (json).getJSONObject("answers").getString("answer_f"),
        ),
        multiple_correct_answers = (json).getBoolean("multiple_correct_answers"),
        correct_answers = if ((json).isNull("correct_answers")) null else {
            CorrectAnswers(
                answer_a_correct = when(json.getJSONObject("correct_answers").getString("answer_a_correct")){
                    "true" -> true
                    "false"->false
                    else -> false
                },
                answer_b_correct = when(json.getJSONObject("correct_answers").getString("answer_b_correct")){
                    "true" -> true
                    "false"->false
                    else -> false
                },
                answer_c_correct = when(json.getJSONObject("correct_answers").getString("answer_c_correct")){
                    "true" -> true
                    "false"->false
                    else -> false
                },
                answer_d_correct = when(json.getJSONObject("correct_answers").getString("answer_d_correct")){
                    "true" -> true
                    "false"->false
                    else -> false
                },
                answer_e_correct = when(json.getJSONObject("correct_answers").getString("answer_e_correct")){
                    "true" -> true
                    "false"->false
                    else -> false
                },
                answer_f_correct = when(json.getJSONObject("correct_answers").getString("answer_f_correct")){
                    "true" -> true
                    "false"->false
                    else -> false
                }
            )
        },
        correct_answer = (json).optString("correct_answer", null),
        explanation = (json).optString("explanation", null),
        tip = (json).optString("tip", null),
        tags = JSONArray((json).getString("tags")).let { jsonArray ->
            List(jsonArray.length()) { index ->
                Tag(jsonArray.getJSONObject(index).getString("name"))
            }
        },
        category = (json).getString("category"),
        difficulty = (json).getString("difficulty")
    )

}

data class QuestionAnswersChoices @JvmOverloads constructor(
    val answer_a: String? = null,
    val answer_b: String? = null,
    val answer_c: String? = null,
    val answer_d: String? = null,
    val answer_e: String? = null,
    val answer_f: String? = null
):java.io.Serializable

data class CorrectAnswers @JvmOverloads constructor(
    val answer_a_correct: Boolean = false,
    val answer_b_correct: Boolean = false,
    val answer_c_correct: Boolean = false,
    val answer_d_correct: Boolean = false,
    val answer_e_correct: Boolean = false,
    val answer_f_correct: Boolean = false
):java.io.Serializable

data class Tag @JvmOverloads constructor(
    val name: String = ""
):java.io.Serializable
