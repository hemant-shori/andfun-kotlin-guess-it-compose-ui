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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.guesstheword.R
import com.example.android.guesstheword.data.GameUiState
import com.example.android.guesstheword.ui.theme.GuessTheAppTheme

@Composable
fun GameScreenContent(uiState: GameUiState, onSkip: () -> Unit, onCorrect: () -> Unit) {
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
                text = stringResource(id = R.string.quote_format, uiState.word),
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
                text = stringResource(R.string.score_format, uiState.score),
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
                    onClick = onSkip,
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
                    onClick = onCorrect,
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


@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GuessTheAppTheme {
        GameScreenContent(
            uiState = GameUiState(),
            onSkip = {},
            onCorrect = {}
        )
    }
}