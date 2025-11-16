package com.example.msproject.ui.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.msproject.model.Question
import com.example.msproject.model.QuizCategory
import androidx.compose.ui.tooling.preview.Preview
import com.example.msproject.ui.theme.MSProjectTheme

@Composable
fun QuizScreen(
    category: QuizCategory,
    onQuizFinished: (score: Int, wrongQuestions: List<Question>, totalQuestions: Int) -> Unit
) {
    // 선택된 카테고리의 문제 리스트
    val questions = remember(category) { getQuestionsForCategory(category) }

    // 현재 문제 인덱스, 점수, 오답 목록 상태
    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    val wrongList = remember { mutableStateListOf<Question>() }

    // 모든 문제를 다 풀었으면 콜백 호출
    if (currentIndex >= questions.size) {
        LaunchedEffect(Unit) {
            onQuizFinished(score, wrongList.toList(), questions.size)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("결과 화면으로 이동 중...")
        }
        return
    }

    val question = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // 상단: 문제 번호
        Text(
            text = "문제 ${currentIndex + 1} / ${questions.size}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 문제 내용
        Text(
            text = question.text,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 보기 4개 버튼
        question.options.forEachIndexed { index, optionText ->
            Button(
                onClick = {
                    // 정답 체크
                    if (index == question.answerIndex) {
                        score += 10   // 문제당 10점
                    } else {
                        wrongList.add(question)
                    }
                    // 다음 문제로
                    currentIndex++
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(optionText)
            }
        }
    }
}

private fun getQuestionsForCategory(category: QuizCategory): List<Question> {
    return when (category) {
        QuizCategory.GENERAL -> listOf(
            Question(
                id = 1,
                category = category,
                text = "",
                options = listOf(),
                answerIndex = 0
            ),
            Question(
                id = 2,
                category = category,
                text = "",
                options = listOf(),
                answerIndex = 2
            )
        )

        QuizCategory.MOVIE -> listOf(
            Question(
                id = 101,
                category = category,
                text = "",
                options = listOf(),
                answerIndex = 2
            )
        )

        QuizCategory.SCIENCE -> listOf(
            Question(
                id = 201,
                category = category,
                text = ,
                options = listOf(),
                answerIndex = 0
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    MSProjectTheme {
        QuizScreen(
            category = QuizCategory.GENERAL,
            onQuizFinished = { _, _, _ -> }
        )
    }
}
