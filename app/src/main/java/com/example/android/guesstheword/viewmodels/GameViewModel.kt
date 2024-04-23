package com.example.android.guesstheword.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    init {
        Log.i("GameViewModel", "created")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "onCleared: destroyed")
    }
}