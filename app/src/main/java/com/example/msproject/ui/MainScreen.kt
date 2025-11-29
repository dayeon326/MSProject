package com.example.msproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
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
            .background(Color(0xFFFDECCE))   // 🔹 배경색 (원하는 색코드로 조정)
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

        // 🔹 트리 + 장식 버튼 레이어 (비율 고정 박스)
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.7f)     // 가로 비율
                .aspectRatio(0.7f)      // 트리 비율 (필요하면 0.6f ~ 0.8f 사이로 조절)
        ) {
            // 트리 이미지
            Image(
                painter = painterResource(id = R.drawable.tree1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )


            // ⭐ 랭킹 (트리 꼭대기)
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Ranking",
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 1.dp)          // 살짝 내려서 트리 꼭대기에 붙이기
                    .clickable { onRankingClick() }
            )

// 🔵 문화&음식 (왼쪽 가지)
            Image(
                painter = painterResource(id = R.drawable.ornament1),
                contentDescription = "Christmas Culture & Food Quiz",
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.Center)   // 기준을 Center로
                    .offset(x = (-25).dp, y = (-20).dp)
                    .clickable { onCategorySelected(QuizCategory.CULTURE) }
            )

// 🟢 영화&음악 (오른쪽 가지)
            Image(
                painter = painterResource(id = R.drawable.ornament2),
                contentDescription = "Movies & Music Quiz",
                modifier = Modifier
                    .size(75.dp)
                    .align(Alignment.Center)
                    .offset(x = 50.dp, y = (40).dp)
                    .clickable { onCategorySelected(QuizCategory.MOVIE) }
            )

// 🔴 일반 상식&역사 (아래 쪽)
            Image(
                painter = painterResource(id = R.drawable.ornament3),
                contentDescription = "General Knowledge & Histor",
                modifier = Modifier
                    .size(65.dp)
                    .align(Alignment.Center)
                    .offset(x = (-60).dp, y = 90.dp)
                    .clickable { onCategorySelected(QuizCategory.SCIENCE) }
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
