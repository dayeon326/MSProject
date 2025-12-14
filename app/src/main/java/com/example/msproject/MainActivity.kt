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
import com.example.msproject.ui.MainScreen
import com.example.msproject.ui.QuizScreen
import com.example.msproject.ui.ResultScreen
import com.example.msproject.ui.RankingScreen
import com.example.msproject.ui.WrongNoteScreen
import com.example.msproject.ui.theme.MSProjectTheme  // ← 프로젝트에 맞게 이름 확인!

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MSProjectTheme {
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
                                    categoryName ?: QuizCategory.CULTURE.name
                                )
                            } catch (e: IllegalArgumentException) {
                                QuizCategory.CULTURE
                            }

                            QuizScreen(
                                category = category,
                                onQuizFinished = { score, wrongQuestions, totalQuestions ->
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
