package com.example.msproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.msproject.model.QuizCategory
import com.example.msproject.model.QuizRepository
import com.example.msproject.ui.main.MainScreen
import com.example.msproject.ui.quiz.QuizScreen
import com.example.msproject.ui.result.ResultScreen
import com.example.msproject.ui.ranking.RankingScreen
import com.example.msproject.ui.wrongnote.WrongNoteScreen
import com.example.msproject.ui.theme.MSProjectTheme  // ← 프로젝트에 맞게 이름 확인!

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MSProjectTheme {   // ← 여기 Theme 이름 프로젝트에 맞게 수정
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        // 메인 화면
                        composable("main") {
                            MainScreen(
                                onCategorySelected = { category ->
                                    // 선택한 카테고리 이름을 경로에 넣어서 전달
                                    navController.navigate("quiz/${category.name}")
                                },
                                onRankingClick = {
                                    navController.navigate("ranking")
                                }
                            )
                        }

                        // 퀴즈 화면
                        composable("quiz/{category}") { backStackEntry ->
                            val categoryName =
                                backStackEntry.arguments?.getString("category")
                            val category = try {
                                QuizCategory.valueOf(
                                    categoryName ?: QuizCategory.GENERAL.name
                                )
                            } catch (e: IllegalArgumentException) {
                                QuizCategory.GENERAL
                            }

                            QuizScreen(
                                category = category,
                                onQuizFinished = { score, wrongQuestions, totalQuestions ->
                                    // 결과를 저장소에 저장
                                    QuizRepository.saveResult(
                                        category = category,
                                        score = score,
                                        totalQuestions = totalQuestions,
                                        wrongQuestions = wrongQuestions
                                    )
                                    // 결과 화면으로 이동
                                    navController.navigate("result")
                                }
                            )
                        }

                        // 결과 화면
                        composable("result") {
                            ResultScreen(
                                score = QuizRepository.lastScore,
                                totalQuestions = QuizRepository.lastTotalQuestions,
                                wrongCount = QuizRepository.lastWrongQuestions.size,
                                onGoMain = {
                                    navController.navigate("main")
                                },
                                onGoWrongNote = {
                                    navController.navigate("wrong_note")
                                }
                            )
                        }

                        // 오답 노트 화면
                        composable("wrong_note") {
                            WrongNoteScreen(
                                wrongQuestions = QuizRepository.lastWrongQuestions,
                                onBackToMain = {
                                    navController.navigate("main")
                                }
                            )
                        }

                        // 랭킹 화면
                        composable("ranking") {
                            RankingScreen(
                                rankingList = QuizRepository.rankingList,
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
