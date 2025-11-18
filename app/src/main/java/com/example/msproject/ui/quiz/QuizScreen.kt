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

//answerIndex 표기 e.g.정답이 2번 보기일 시 값은 1(인덱스 0부터 시작)
private fun getQuestionsForCategory(category: QuizCategory): List<Question> {
    return when (category) {
        QuizCategory.CULTURE -> listOf(
            Question(
                id = 101,
                category = category,
                text = "크리스마스 전통 음식 '에그노그'의 주요 재료는?",
                options = listOf(
                    "달걀",
                    "감자",
                    "레몬",
                    "연어"
                ),
                answerIndex = 0
            ),
            Question(
                id = 102,
                category = category,
                text = "영국에서 크리스마스 때 먹는 크리스마스 푸딩의 특징은?",
                options = listOf(
                    "얼려 먹는 디저트",
                    "불을 붙여 내기도 한다",
                    "아이스크림을 올려 먹는다",
                    "국물에 말아 먹는다"
                ),
                answerIndex = 1
            ),
            Question(
                id = 103,
                category = category,
                text = "프랑스의 크리스마스 전통 케이크 '뷔슈 드 노엘'은 무엇을 형상화한 것일까?",
                options = listOf(
                    "눈사람",
                    "루돌프",
                    "통나무",
                    "눈송이"
                ),
                answerIndex = 2
            ),
            Question(
                id = 104,
                category = category,
                text = "이탈리아에서 크리스마스 시즌에 먹는 전통 빵은?",
                options = listOf(
                    "포카치아",
                    "파네토네",
                    "바움쿠헨",
                    "라자냐"
                ),
                answerIndex = 1
            ),
            Question(
                id = 105,
                category = category,
                text = "크리스마스 양말을 걸어두는 이유는?",
                options = listOf(
                    "장식용으로",
                    "양말이 눈에 젖어서",
                    "악몽을 막기 위해",
                    "산타가 선물을 넣기 위해"
                ),
                answerIndex = 3
            ),
            Question(
                id = 106,
                category = category,
                text = "미국에서 크리스마스 트리에 자주 사용하는 장식 캔디케인의 전통 색은?",
                options = listOf(
                    "파랑+하양",
                    "초록+금색",
                    "초록+빨강",
                    "빨강+하양"
                ),
                answerIndex = 3
            ),
            Question(
                id = 107,
                category = category,
                text = "핀란드에서는 크리스마스에 어떤 것을 하는 전통이 있을까?",
                options = listOf(
                    "사우나를 한다",
                    "스키 대회를 한다",
                    "캠핑을 간다",
                    "강에서 낚시를 한다"
                ),
                answerIndex = 0
            ),
            Question(
                id = 108,
                category = category,
                text = "멕시코 크리스마스 전통 중 포사다(Posada)는?",
                options = listOf(
                    "눈싸움 축제",
                    "마을 행진과 노래",
                    "꽃 장식 만들기",
                    "큰 폭죽 터트리기"
                ),
                answerIndex = 1
            ),
            Question(
                id = 109,
                category = category,
                text = "진저브레드 하우스를 만드는 전통은 어느 이야기에서 영감을 받았을까?",
                options = listOf(
                    "나니아 연대기",
                    "눈의 여왕",
                    "헨젤과 그레텔",
                    "성냥팔이 소녀"
                ),
                answerIndex = 2
            ),
            Question(
                id = 110,
                category = category,
                text = "독일의 전통에서는 슈톨렌을 보통 언제 만드는가?",
                options = listOf(
                    "크리스마스 1달 전",
                    "크리스마스 이브",
                    "크리스마스 당일 자정",
                    "크리스마스 당일 정오"
                ),
                answerIndex = 0
            ),
            Question(
                id = 111,
                category = category,
                text = "크리스마스 시즌마다 전 세계에서 가장 많이 공연되는 발레 작품은 무엇일까?",
                options = listOf(
                    "지젤",
                    "돈키호테",
                    "백조의 호수",
                    "호두까기 인형"
                ),
                answerIndex = 3
            ),
            Question(
                id = 112,
                category = category,
                text = "영국의 크리스마스 디저트 '민스 파이'에 현대에 주로 들어가는 재료는?",
                options = listOf(
                    "감자와 치즈",
                    "초콜릿 크림",
                    "말린 과일",
                    "다진 고기"
                ),
                answerIndex = 2
            ),
            Question(
                id = 113,
                category = category,
                text = "일본에서 크리스마스 때 즐겨 먹는 대표 음식은?",
                options = listOf(
                    "치킨",
                    "모찌",
                    "우나기 덮밥",
                    "링고아메"
                ),
                answerIndex = 0
            ),
            Question(
                id = 114,
                category = category,
                text = "스웨덴의 크리스마스 음료 '율무스트(Julmust)'는 어떤 종류의 음료인가?",
                options = listOf(
                    "알코올 음료",
                    "탄산 음료",
                    "허브 차",
                    "라떼"
                ),
                answerIndex = 1
            ),
            Question(
                id = 115,
                category = category,
                text = "네덜란드에서는 아이들이 양말 대신 무엇을 두고 크리스마스 선물을 기다릴까?",
                options = listOf(
                    "모자",
                    "바구니",
                    "장갑",
                    "신발"
                ),
                answerIndex = 3
            ),
        )

        QuizCategory.MOVIE -> listOf(
            Question(
                id = 201,
                category = category,
                text = "",
                options = listOf(),
                answerIndex = 2
            )
        )

        QuizCategory.SCIENCE -> listOf(
            Question(
                id = 301,
                category = category,
                text ="",
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
            category = QuizCategory.CULTURE,
            onQuizFinished = { _, _, _ -> }
        )
    }
}
