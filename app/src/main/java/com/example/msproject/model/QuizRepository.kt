package com.example.msproject.model

object QuizRepository {

    var lastScore: Int = 0
        private set

    var lastTotalQuestions: Int = 0
        private set

    var lastWrongQuestions: List<Question> = emptyList()
        private set

    // 랭킹용 리스트 (앱 켜져 있는 동안만 유지)
    val rankingList: MutableList<ScoreRecord> = mutableListOf()

    fun saveResult(
        category: QuizCategory,
        score: Int,
        totalQuestions: Int,
        wrongQuestions: List<Question>
    ) {
        lastScore = score
        lastTotalQuestions = totalQuestions
        lastWrongQuestions = wrongQuestions

        // 랭킹 기록 추가
        val record = ScoreRecord(
            category = category,
            score = score,
            dateTime = System.currentTimeMillis()
        )
        rankingList.add(record)
    }
}
