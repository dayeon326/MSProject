package com.example.msproject.model

data class Question(
    val id: Int,
    val category: QuizCategory,
    val text: String,
    val options: List<String>,   // 보기 4개
    val answerIndex: Int         // 0~3 중 정답 인덱스
)
