package com.example.msproject.ui.result

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.msproject.ui.theme.MSProjectTheme
@Composable
fun ResultScreen(
    score: Int,              // 최종 점수
    totalQuestions: Int,     // 전체 문제 수
    wrongCount: Int,         // 틀린 문제 개수
    onGoMain: () -> Unit,    // 메인 화면으로
    onGoWrongNote: () -> Unit // 오답 노트로
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "퀴즈 결과",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "최종 점수 : ${score}점",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "총 문제 수 : $totalQuestions 문제",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "틀린 문제 : $wrongCount 문제",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onGoWrongNote,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("오답 노트 보기")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onGoMain,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("메인으로 돌아가기")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MSProjectTheme {
        ResultScreen(
            score = 80,
            totalQuestions = 10,
            wrongCount = 2,
            onGoMain = {},
            onGoWrongNote = {}
        )
    }
}
