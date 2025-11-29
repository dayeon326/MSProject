package com.example.msproject.ui

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
import com.example.msproject.model.ScoreRecord
import com.example.msproject.model.QuizCategory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.example.msproject.ui.theme.MSProjectTheme
@Composable
fun RankingScreen(
    rankingList: List<ScoreRecord>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // 상단 제목
        Text(
            text = "랭킹",
            style = MaterialTheme.typography.headlineMedium
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

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("뒤로가기")
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
            style = MaterialTheme.typography.titleMedium
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