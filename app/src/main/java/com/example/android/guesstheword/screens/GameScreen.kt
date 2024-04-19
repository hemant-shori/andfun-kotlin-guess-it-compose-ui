package com.example.android.guesstheword.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.guesstheword.R
import com.example.android.guesstheword.ui.theme.GuessTheAppTheme


// The current score
private var score = 0

// The list of words - the front of the list is the next word to guess
private var wordList: MutableList<String> = mutableListOf(
    "queen",
    "hospital",
    "basketball",
    "cat",
    "change",
    "snail",
    "soup",
    "calendar",
    "sad",
    "desk",
    "guitar",
    "home",
    "railway",
    "zebra",
    "jelly",
    "car",
    "crow",
    "trade",
    "bag",
    "roll",
    "bubble"
).also { it.shuffle() }

@Composable
fun GameScreenContent(onGameFinished: (score: Int) -> Unit) {
    var mutableWord = remember {
        mutableStateOf(wordList.removeAt(0))
    }
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            // Word is text
            Text(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.vertical_margin)),
                text = stringResource(R.string.word_is),
                style = MaterialTheme.typography.bodyLarge,
            )
            // Guess the Word text
            Text(
                text = stringResource(id = R.string.quote_format, mutableWord.value),
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.weight(1f))
            // Timer
            Text(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.vertical_margin)),
                // TODO implement timer
                text = "",
                style = MaterialTheme.typography.bodyLarge,
            )
            // Score
            Text(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.vertical_margin)),
                text = stringResource(R.string.score_format, score),
                style = MaterialTheme.typography.bodyLarge,
            )
            // Buttons row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.vertical_margin)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Skip button
                Button(
                    onClick = { onSkip(mutableWord, onGameFinished) },
                    modifier = Modifier
                        .padding(
                            bottom = dimensionResource(id = R.dimen.vertical_margin),
                            start = dimensionResource(id = R.dimen.button_padding)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.skip),
                        fontWeight = FontWeight.Bold,
                    )
                }
                // Got it button
                Button(
                    onClick = { onCorrect(mutableWord, onGameFinished) },
                    modifier = Modifier
                        .padding(
                            bottom = dimensionResource(id = R.dimen.vertical_margin),
                            end = dimensionResource(id = R.dimen.button_padding)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.got_it),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

/** Methods for skip button **/
private fun onSkip(mutableWord: MutableState<String>, gameFinished: (score: Int) -> Unit) {
    score--
    nextWord(mutableWord, gameFinished)
}

/** Methods for correct button **/
private fun onCorrect(mutableWord: MutableState<String>, gameFinished: (score: Int) -> Unit) {
    score++
    nextWord(mutableWord, gameFinished)
}

/**
 * Moves to the next word in the list
 */
private fun nextWord(mutableWord: MutableState<String>, gameFinished: (score: Int) -> Unit) {
    //Select and remove a word from the list
    if (wordList.isEmpty()) {
        gameFinished(score)
    } else {
        mutableWord.value = wordList.removeAt(0)
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GuessTheAppTheme {
        GameScreenContent {}
    }
}