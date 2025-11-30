package com.example.msproject.ui

import androidx.compose.foundation.Image // 이미지 추가
import androidx.compose.foundation.BorderStroke // 테두리 색상용 추가
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // 버튼 색상용 추가
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // 색상 정의용 추가
import androidx.compose.ui.layout.ContentScale // 배경 꽉 채우기
import androidx.compose.ui.res.painterResource // 리소스 불러오기
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.msproject.ui.theme.MSProjectTheme
import com.example.msproject.R // R 파일 임포트 필수

@Composable
fun ResultScreen(
    score: Int,              // 최종 점수
    totalQuestions: Int,     // 전체 문제 수
    wrongCount: Int,         // 틀린 문제 개수
    onGoMain: () -> Unit,    // 메인 화면으로
    onGoWrongNote: () -> Unit // 오답 노트로
) {
    // [색상 정의] 퀴즈 화면과 동일한 연한 버건디
    val burgundyColor = Color(0xFFA03040)

    // [구조 변경] Box로 감싸서 배경 이미지 적용
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

        // 2. 결과 내용
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // 화면 정중앙 배치
        ) {

            Text(
                text = "퀴즈 결과",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "최종 점수 : ${score}점",
                style = MaterialTheme.typography.headlineSmall, // 점수는 좀 더 크게
                fontWeight = FontWeight.Bold,
                color = burgundyColor // 점수에 포인트 컬러
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "총 문제 수 : $totalQuestions 문제",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "틀린 문제 : $wrongCount 문제",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(48.dp))

            // [버튼 1] 오답 노트 보기 (채워진 버건디 버튼)
            Button(
                onClick = onGoWrongNote,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = burgundyColor,
                    contentColor = Color.White
                )
            ) {
                Text("오답 노트 보기", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // [버튼 2] 메인으로 돌아가기 (테두리만 있는 버튼)
            OutlinedButton(
                onClick = onGoMain,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                // 글씨색과 테두리색을 버건디로 변경
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = burgundyColor
                ),
                border = BorderStroke(1.dp, burgundyColor)
            ) {
                Text("메인으로 돌아가기", style = MaterialTheme.typography.bodyLarge)
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