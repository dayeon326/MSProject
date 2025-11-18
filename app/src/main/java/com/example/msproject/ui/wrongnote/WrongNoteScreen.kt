package com.example.msproject.ui.wrongnote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.msproject.model.Question
import androidx.compose.ui.tooling.preview.Preview
import com.example.msproject.ui.theme.MSProjectTheme
import com.example.msproject.model.QuizCategory
@Composable
fun WrongNoteScreen(
    wrongQuestions: List<Question>, // 틀린 문제 리스트
    onBackToMain: () -> Unit        // 메인으로 돌아가기
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // 상단 제목
        Text(
            text = "오답 노트",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (wrongQuestions.isEmpty()) {
            // 틀린 문제가 하나도 없을 때
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("틀린 문제가 없습니다! 🎉")
            }
        } else {
            // 틀린 문제 목록
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                itemsIndexed(wrongQuestions) { index, question ->
                    WrongQuestionItem(
                        index = index + 1,
                        question = question
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackToMain,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("메인으로 돌아가기")
        }
    }
}

@Composable
private fun WrongQuestionItem(
    index: Int,
    question: Question
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = "Q$index. ${question.text}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        question.options.forEachIndexed { i, option ->
            val label = when (i) {
                0 -> "①"
                1 -> "②"
                2 -> "③"
                3 -> "④"
                else -> "-"
            }

            Text(text = "$label $option")
        }

        Spacer(modifier = Modifier.height(8.dp))

        val correctLabel = when (question.answerIndex) {
            0 -> "①"
            1 -> "②"
            2 -> "③"
            3 -> "④"
            else -> "-"
        }

        Text(
            text = "정답: $correctLabel",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WrongNoteScreenPreview() {
    MSProjectTheme {
        WrongNoteScreen(
            wrongQuestions = listOf(
                Question(
                    id = 1,
                    category = QuizCategory.CULTURE,
                    text = "프리뷰 예시 문제입니다.",
                    options = listOf("1번", "2번", "3번", "4번"),
                    answerIndex = 0
                )
            ),
            onBackToMain = {}
        )
    }
}