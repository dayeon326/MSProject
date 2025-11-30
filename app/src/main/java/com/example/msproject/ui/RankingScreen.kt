package com.example.msproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // [추가] 버튼 색상 변경용
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // [추가] 색상 정의용
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight // [추가] 폰트 두께 조절용
import androidx.compose.ui.unit.dp
import com.example.msproject.model.ScoreRecord
import com.example.msproject.model.QuizCategory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.example.msproject.ui.theme.MSProjectTheme
import com.example.msproject.R

@Composable
fun RankingScreen(
    rankingList: List<ScoreRecord>,
    onBack: () -> Unit
) {
    // [색상 정의]
    val burgundyColor = Color(0xFFA03040) // 퀴즈 화면과 동일한 연한 버건디
    val goldYellowColor = Color(0xFFE0A800) // 랭킹 제목용 노란색

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

        // 2. 랭킹 내용
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            // 상단 제목 [수정됨]
            Text(
                text = "랭킹",
                style = MaterialTheme.typography.headlineMedium,
                color = goldYellowColor, // 노란색 적용
                fontWeight = FontWeight.Bold // 두껍게 적용
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (rankingList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("아직 랭킹 기록이 없습니다.")
                }
            } else {
                val sortedList = rankingList.sortedByDescending { it.score }

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(sortedList) { index, record ->
                        RankingItem(
                            rank = index + 1,
                            record = record
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 뒤로가기 버튼 [수정됨]
            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp), // 버튼 높이도 퀴즈 화면과 맞춤
                colors = ButtonDefaults.buttonColors(
                    containerColor = burgundyColor, // 버건디색 적용
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "뒤로가기",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun RankingItem(
    rank: Int,
    record: ScoreRecord
) {
    val dateText = rememberFormattedDate(record.dateTime)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = "${rank}위 - ${record.category.title}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold // 랭킹 항목 제목도 살짝 두껍게
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "점수: ${record.score}점",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "날짜: $dateText",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun rememberFormattedDate(timeMillis: Long): String {
    val date = Date(timeMillis)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
    return format.format(date)
}

@Preview(showBackground = true)
@Composable
fun RankingScreenPreview() {
    MSProjectTheme {
        RankingScreen(
            rankingList = listOf(
                ScoreRecord(
                    category = QuizCategory.CULTURE,
                    score = 90,
                    dateTime = System.currentTimeMillis()
                ),
                ScoreRecord(
                    category = QuizCategory.MOVIE,
                    score = 70,
                    dateTime = System.currentTimeMillis() - 1000000
                )
            ),
            onBack = {}
        )
    }
}