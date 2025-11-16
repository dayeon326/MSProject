package com.example.msproject.model

data class ScoreRecord(
    val category: QuizCategory,
    val score: Int,
    val dateTime: Long // 저장용 타임스탬프
)

