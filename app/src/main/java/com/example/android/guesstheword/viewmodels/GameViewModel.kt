package com.example.android.guesstheword.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.data.DataSource
import com.example.android.guesstheword.data.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        Log.i("GameViewModel", "created")
        resetWords()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "onCleared: destroyed")
    }

    fun resetWords() {
        DataSource.wordList.shuffle()
        _uiState.value = GameUiState(
            wordList = DataSource.wordList
        )
        // load the first word
        nextWord()
    }

    /** Methods for skip button **/
    fun onSkip() {
        _uiState.update { currentState ->
            currentState.copy(
                score = currentState.score - 1,
            )
        }
        nextWord()
    }

    /** Methods for correct button **/
    fun onCorrect() {
        _uiState.update { currentState ->
            currentState.copy(
                score = currentState.score + 1,
            )
        }
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (_uiState.value.wordList.isEmpty()) {
            //gameFinished(score)
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    word = currentState.wordList.removeAt(0)
                )
            }
        }
    }
}