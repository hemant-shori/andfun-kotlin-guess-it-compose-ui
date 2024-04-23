package com.example.android.guesstheword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android.guesstheword.screens.GameScreenContent
import com.example.android.guesstheword.screens.ScoreScreenContent
import com.example.android.guesstheword.screens.TitleScreenContent
import com.example.android.guesstheword.ui.theme.GuessTheAppTheme
import com.example.android.guesstheword.viewmodels.GameViewModel

/*
 * enum class to define the routes.
 */
enum class GuessTheWordAppScreen {
    TitleScreen,
    GameScreen,
    ScoreScreen
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        setContent {
            GuessTheAppTheme {
                ScaffoldRootContent(screenTitle = stringResource(R.string.app_name),
                    gameViewModel = gameViewModel
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldRootContent(
        navigationController: NavHostController = rememberNavController(),
        screenTitle: String,
        gameViewModel: GameViewModel = viewModel()
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.secondary,
            topBar = {
                TopAppBar(
                    title = { Text(text = screenTitle) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        titleContentColor = MaterialTheme.colorScheme.onSecondary
                    )
                )
            },
            modifier = Modifier.background(color = Color.Yellow),
            content = { paddingValues ->
                GameNavHost(navigationController, paddingValues, gameViewModel)
            }
        )
    }

    @Composable
    private fun GameNavHost(
        navigationController: NavHostController,
        paddingValues: PaddingValues,
        gameViewModel: GameViewModel,
    ) {
        val uiState by gameViewModel.uiState.collectAsState()
        NavHost(
            navController = navigationController,
            startDestination = GuessTheWordAppScreen.TitleScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = GuessTheWordAppScreen.TitleScreen.name) {
                TitleScreenContent {
                    navigationController.navigate(GuessTheWordAppScreen.GameScreen.name)
                }
            }

            composable(route = GuessTheWordAppScreen.GameScreen.name) {
                GameScreenContent(
                    uiState = uiState,
                    onSkip = {
                        gameViewModel.onSkip()
                    },
                    onCorrect = {
                        gameViewModel.onCorrect()
                    })
            }

            composable(route = GuessTheWordAppScreen.ScoreScreen.name) {
                ScoreScreenContent(uiState.score.toString()) {
                    // Shuffles the words
                    gameViewModel.resetWords()
                    navigationController.navigate(GuessTheWordAppScreen.TitleScreen.name)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun RootGamePreview() {
        val gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        GuessTheAppTheme {
            ScaffoldRootContent(screenTitle = stringResource(R.string.app_name),
                gameViewModel = gameViewModel
            )
        }
    }
}
