package com.creativeitinstitute.quiz2501

data class Quiz(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
