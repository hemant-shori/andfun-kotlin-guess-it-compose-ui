package com.example.android.guesstheword.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.android.guesstheword.ui.theme.GuessTheAppTheme

@Composable
fun ScoreScreenContent(score: String, onPlayAgainButtonClicked: () -> Unit) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.vertical_margin)),
                text = stringResource(R.string.you_scored),
                style = MaterialTheme.typography.bodyLarge,
            )

            Text(
                text = score,
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onPlayAgainButtonClicked,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = dimensionResource(id = R.dimen.vertical_margin)),
            ) {
                Text(
                    text = stringResource(id = R.string.play_again),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreScreenPreview() {
    GuessTheAppTheme {
        ScoreScreenContent("40") { }
    }
}