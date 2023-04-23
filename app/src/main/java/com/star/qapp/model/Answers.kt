package com.star.qapp.model
//
//data class Answers (
//        val id : Int,
//        val question: String,//the question at hand
//        val description: String?,
//        var answers: List<Answer>,
//        val multipleCorrectAnswers: Boolean,
//        var correctAnswers: List<Answer>,
//        val correct_answer: Answer?,
//        val explanation: String?,
//        val tip: String?,
//        var tags: List<Tag>,//set since every tag will be unique
//        val category: String,
//        val difficulty : String
//        ){
//
//        constructor() : this(0, "", null, emptyList(), false, emptyList(), null, null, null, emptyList(), "", "")
//}
//
//data class Answer(
//        val title :String,
//        val correct : Boolean
//        ){
//
//        constructor() :  this("", false)
//}
//data class Tag(
//        val name: String,
//){
//        constructor() : this("")
//}