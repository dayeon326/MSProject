package com.example.msproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.msproject.R
import com.example.msproject.model.QuizCategory
import com.example.msproject.ui.theme.MSProjectTheme
import com.example.msproject.ui.theme.MainFont


@Composable
fun MainScreen(
    onCategorySelected: (QuizCategory) -> Unit,
    onRankingClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDECCE))
    ) {
        Text(
            text = "Tap a decoration to navigate!",
            fontFamily = MainFont,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 120.dp)
        )

        // 🔹 트리 + 장식 버튼 레이어
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.7f)
                .aspectRatio(0.7f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.tree1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )


            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Ranking",
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 1.dp)
                    .clickable { onRankingClick() }
            )

            Image(
                painter = painterResource(id = R.drawable.ornament1),
                contentDescription = "Christmas Culture & Food Quiz",
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.Center)   // 기준을 Center로
                    .offset(x = (-25).dp, y = (-20).dp)
                    .clickable { onCategorySelected(QuizCategory.CULTURE) }
            )

            Image(
                painter = painterResource(id = R.drawable.ornament2),
                contentDescription = "Movies & Music Quiz",
                modifier = Modifier
                    .size(75.dp)
                    .align(Alignment.Center)
                    .offset(x = 50.dp, y = (40).dp)
                    .clickable { onCategorySelected(QuizCategory.MOVIE) }
            )

            Image(
                painter = painterResource(id = R.drawable.ornament3),
                contentDescription = "General Knowledge & Histor",
                modifier = Modifier
                    .size(65.dp)
                    .align(Alignment.Center)
                    .offset(x = (-60).dp, y = 90.dp)
                    .clickable { onCategorySelected(QuizCategory.KNOWLEDGE) }
            )
        }
        // 안내 박스
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "⭐ →  Ranking",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = MainFont,
                    color = Color.Black
                )
                Text(
                    text = "🔵 →  Christmas Culture & Food Quiz",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = MainFont,
                    color = Color.Black
                )

                Text(
                    text = "🟢 →  Movies & Music Quiz",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = MainFont,
                    color = Color.Black
                )

                Text(
                    text = "🔴 →  General Knowledge Quiz",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = MainFont,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MSProjectTheme {
        MainScreen(
            onCategorySelected = {},
            onRankingClick = {}
        )
    }
}
