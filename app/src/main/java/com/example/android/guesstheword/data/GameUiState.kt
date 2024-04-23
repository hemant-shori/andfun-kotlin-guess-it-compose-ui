 package com.example.android.guesstheword.data

data class GameUiState(
    val word: String = "",
    val score: Int = 0,
    val wordList: MutableList<String> = mutableListOf("")
)
