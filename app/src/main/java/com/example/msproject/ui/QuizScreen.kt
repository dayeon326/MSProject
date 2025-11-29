package com.example.msproject.ui

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
import android.media.MediaPlayer
import androidx.compose.ui.platform.LocalContext
import com.example.msproject.R

@Composable
fun QuizScreen(
    category: QuizCategory,
    onQuizFinished: (score: Int, wrongQuestions: List<Question>, totalQuestions: Int) -> Unit
) {
    // [추가 1] 소리를 재생하려면 'Context'가 필요합니다.
    val context = LocalContext.current

    // 선택된 카테고리의 문제 리스트
    val questions = remember(category) { getQuestionsForCategory(category) }

    // 현재 문제 인덱스, 점수, 오답 목록 상태
    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    val wrongList = remember { mutableStateListOf<Question>() }

    // [추가 2] 소리 재생 함수 만들기 (함수 안에 함수를 넣습니다)
    fun playSound(isCorrect: Boolean) {
        // 정답이면 correct_sound, 오답이면 wrong_sound (파일명 확인 필수!)
        val soundResId = if (isCorrect) R.raw.correct_sound else R.raw.wrong_sound

        // 미디어 플레이어 생성 및 시작
        val mediaPlayer = MediaPlayer.create(context, soundResId)
        mediaPlayer?.start()

        // 소리가 다 끝나면 메모리 청소
        mediaPlayer?.setOnCompletionListener { mp ->
            mp.release()
        }
    }

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
                    // [추가 3] 정답인지 먼저 판단하고 소리 내기!
                    val isCorrect = (index == question.answerIndex)
                    playSound(isCorrect) // 소리 재생!

                    // 정답 체크
                    if (isCorrect) {
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
                text = "영화 '겨울왕국'에서 엘사가 부른 유명한 주제곡은?",
                options = listOf("Let It Go", "곰 세 마리", "작은 별", "아리랑"),
                answerIndex = 0 // Let It Go [cite: 122, 123]
            ),
            Question(
                id = 202,
                category = category,
                text = "봉준호 감독이 만드신 영화는?",
                options = listOf("아리랑", "곰 세 마리", "아이언맨", "기생충"),
                answerIndex = 3 // 기생충 [cite: 127, 131]
            ),
            Question(
                id = 203,
                category = category,
                text = "아이언맨 영화의 주인공 이름은?",
                options = listOf("다니엘", "토니 스타크", "샘", "윌 스미스"),
                answerIndex = 1 // 토니 스타크 [cite: 132, 134]
            ),
            Question(
                id = 204,
                category = category,
                text = "헐크 영화의 주인공 이름은?",
                options = listOf("다니엘", "루이", "토니 스타크", "브루스 배너"),
                answerIndex = 3 // 브루스 배너 [cite: 137, 141]
            ),
            Question(
                id = 205,
                category = category,
                text = "영화 '해리 포터' 시리즈에서 마법을 배우는 학생들이 다니는 학교의 이름은?",
                options = listOf("킹스 칼리지", "옥스퍼드", "캠브리지", "호그와트"),
                answerIndex = 3 // 호그와트 [cite: 142, 146]
            ),
            Question(
                id = 206,
                category = category,
                text = "마블의 슈퍼 히어로 팀 이름은 무엇인가요?",
                options = listOf("저스티스 리그", "판타스틱 4", "어벤져스", "엑스맨"),
                answerIndex = 2 // 어벤져스 [cite: 147, 150]
            ),
            Question(
                id = 207,
                category = category,
                text = "영화 '스타워즈'에서 '내가 네 아버지다'라는 명대사를 남긴 악당은?",
                options = listOf("요다", "루크 스카이워커", "한 솔로", "다스 베이더"),
                answerIndex = 3 // 다스 베이더 [cite: 152, 156]
            ),
            Question(
                id = 208,
                category = category,
                text = "영화 '매트릭스' 시리즈의 주인공 이름은 무엇인가요?",
                options = listOf("모피어스", "네오", "트리니티", "스미스 요원"),
                answerIndex = 1 // 네오 [cite: 157, 159]
            ),
            Question(
                id = 209,
                category = category,
                text = "2012년 '강남스타일' 노래와 '말춤'으로 전 세계적인 인기를 얻은 가수는?",
                options = listOf("비", "싸이", "유재석", "아이유"),
                answerIndex = 1 // 싸이 [cite: 162, 164]
            ),
            Question(
                id = 210,
                category = category,
                text = "영화 '라이온 킹'의 주인공 심바는 어떤 동물인가요?",
                options = listOf("호랑이", "사자", "코끼리", "하이에나"),
                answerIndex = 1 // 사자 [cite: 167, 169]
            ),
            Question(
                id = 211,
                category = category,
                text = "'스파이더맨'은 어떤 동물과 관련된 능력을 가지고 있나요?",
                options = listOf("박쥐", "거미", "개미", "독수리"),
                answerIndex = 1 // 거미 [cite: 172, 174]
            ),
            Question(
                id = 212,
                category = category,
                text = "다음 중 손가락으로 눌러서 소리를 내는 악기는?",
                options = listOf("피아노", "트럼펫", "바이올린", "드럼"),
                answerIndex = 0 // 피아노 [cite: 177, 178]
            ),
            Question(
                id = 213,
                category = category,
                text = "어벤져스 히어로 중, 초록색 거인으로 변하는 캐릭터는?",
                options = listOf("아이언맨", "헐크", "토르", "스파이더맨"),
                answerIndex = 1 // 헐크 [cite: 182, 184]
            ),
            Question(
                id = 214,
                category = category,
                text = "영화 '극한직업'은 주인공들이 경찰이지만 무엇을 팔았나요?",
                options = listOf("커피", "치킨", "떡볶이", "빵"),
                answerIndex = 1 // 치킨 [cite: 187, 189]
            ),
            Question(
                id = 215,
                category = category,
                text = "2024년에 개봉한 픽사 애니메이션으로, '기쁨', '슬픔' 등이 나오는 영화는?",
                options = listOf("엘리멘탈", "인사이드 아웃", "쿵푸팬더 4", "업"),
                answerIndex = 1 // 인사이드 아웃 [cite: 192, 194]
            )
        )

        QuizCategory.SCIENCE -> listOf(
            Question(
                id = 301,
                category = category,
                text = "1년은 총 며칠인가요?",
                options = listOf("100일", "255일", "365일", "1000일"),
                answerIndex = 2 // 365일 [cite: 46, 49]
            ),
            Question(
                id = 302,
                category = category,
                text = "대한민국의 수도는 어디인가요?",
                options = listOf("부산", "서울", "인천", "대구"),
                answerIndex = 1 // 서울 [cite: 51, 53]
            ),
            Question(
                id = 303,
                category = category,
                text = "10월 9일, 한글날은 누구를 기념하는 날인가요?",
                options = listOf("이순신 장군", "세종대왕", "유관순 열사", "안중근 의사"),
                answerIndex = 1 // 세종대왕 [cite: 56, 58]
            ),
            Question(
                id = 304,
                category = category,
                text = "불이 났을 때 전화해야 하는 번호는?",
                options = listOf("112", "119", "120", "131"),
                answerIndex = 1 // 119 [cite: 61, 63]
            ),
            Question(
                id = 305,
                category = category,
                text = "경찰과 관련있는 전화번호는?",
                options = listOf("112", "119", "120", "131"),
                answerIndex = 0 // 112 [cite: 66, 67]
            ),
            Question(
                id = 306,
                category = category,
                text = "해가 뜨는 쪽은 어느 쪽인가요?",
                options = listOf("동쪽", "서쪽", "남쪽", "북쪽"),
                answerIndex = 0 // 동쪽 [cite: 71, 72]
            ),
            Question(
                id = 307,
                category = category,
                text = "거북선을 만들어 왜군을 물리친 조선 시대 장군은?",
                options = listOf("김유신", "을지문덕", "이순신", "강감찬"),
                answerIndex = 2 // 이순신 [cite: 76, 79]
            ),
            Question(
                id = 308,
                category = category,
                text = "8월 15일은 무슨날일까요?",
                options = listOf("삼일절", "제헌절", "광복절", "개천절"),
                answerIndex = 2 // 광복절 [cite: 81, 84]
            ),
            Question(
                id = 309,
                category = category,
                text = "우리나라의 국기 이름은?",
                options = listOf("애국가", "무궁화", "태극기", "한글"),
                answerIndex = 2 // 태극기 [cite: 86, 89]
            ),
            Question(
                id = 310,
                category = category,
                text = "1년 12달 중, 날짜 수가 가장 적은 달은?",
                options = listOf("1월", "2월", "7월", "12월"),
                answerIndex = 1 // 2월 [cite: 91, 93]
            ),
            Question(
                id = 311,
                category = category,
                text = "어린이날은 몇 월 며칠인가요?",
                options = listOf("3월 3일", "5월 5일", "6월 6일", "8월 15일"),
                answerIndex = 1 // 5월 5일 [cite: 96, 98]
            ),
            Question(
                id = 312,
                category = category,
                text = "물을 얼리면 무엇이 되나요?",
                options = listOf("수증기", "얼음", "이슬", "소금"),
                answerIndex = 1 // 얼음 [cite: 101, 103]
            ),
            Question(
                id = 313,
                category = category,
                text = "우리나라의 국화(나라꽃)는 무엇인가요?",
                options = listOf("진달래", "개나리", "무궁화", "장미"),
                answerIndex = 2 // 무궁화 [cite: 106, 109]
            ),
            Question(
                id = 314,
                category = category,
                text = "하루는 총 몇 시간으로 이루어져 있나요?",
                options = listOf("12시간", "20시간", "24시간", "30시간"),
                answerIndex = 2 // 24시간 [cite: 111, 114]
            ),
            Question(
                id = 315,
                category = category,
                text = "고구려, 백제, 신라 세 나라가 있던 시대를 무엇이라고 부르나요?",
                options = listOf("조선 시대", "고려 시대", "통일 신라 시대", "삼국 시대"),
                answerIndex = 3 // 삼국 시대 [cite: 116, 120]
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
