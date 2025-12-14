package com.example.msproject.ui

import androidx.compose.foundation.Image // 이미지 추가
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // 버튼 색상용 추가
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // 색상 정의용 추가
import androidx.compose.ui.layout.ContentScale // 배경 꽉 채우기
import androidx.compose.ui.res.painterResource // 리소스 불러오기
import androidx.compose.ui.text.font.FontWeight // 글씨 두께용 추가
import androidx.compose.ui.unit.dp
import com.example.msproject.model.Question
import androidx.compose.ui.tooling.preview.Preview
import com.example.msproject.ui.theme.MSProjectTheme
import com.example.msproject.model.QuizCategory
import com.example.msproject.R // R 파일 임포트 필수

@Composable
fun WrongNoteScreen(
    wrongQuestions: List<Question>, // 틀린 문제 리스트
    onBackToMain: () -> Unit        // 메인으로 돌아가기
) {
    val burgundyColor = Color(0xFFA03040)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. 배경 이미지
        Image(
            painter = painterResource(id = R.drawable.christmas_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.3f
        )

        // 2. 오답 노트 내용
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            // 상단 제목
            Text(
                text = "오답 노트",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
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
                            question = question,
                            highlightColor = burgundyColor
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 메인으로 돌아가기 버튼
            Button(
                onClick = onBackToMain,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = burgundyColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "메인으로 돌아가기",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun WrongQuestionItem(
    index: Int,
    question: Question,
    highlightColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = "Q$index. ${question.text}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
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

        // 정답 표시
        Text(
            text = "정답: $correctLabel",
            style = MaterialTheme.typography.bodyMedium,
            color = highlightColor,
            fontWeight = FontWeight.Bold
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