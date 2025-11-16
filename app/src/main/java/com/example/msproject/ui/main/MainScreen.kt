package com.example.msproject.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.msproject.model.QuizCategory
import androidx.compose.ui.tooling.preview.Preview
import com.example.msproject.ui.theme.MSProjectTheme
@Composable
fun MainScreen(
    onCategorySelected: (QuizCategory) -> Unit,
    onRankingClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        QuizCategory.values().forEach { category ->
            Button(
                onClick = { onCategorySelected(category) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 랭킹 보기
        OutlinedButton(
            onClick = onRankingClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("랭킹 보기")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MSProjectTheme {
        MainScreen(
            onCategorySelected = { },
            onRankingClick = { }
        )
    }
}

