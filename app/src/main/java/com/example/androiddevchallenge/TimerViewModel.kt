package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class TimerViewModel : ViewModel() {

    var started by mutableStateOf(false)
    var startedRoutine : Job = Job()

    var encoded by mutableStateOf(true)
    var showExplanation by mutableStateOf(true)

    var countdownTimer by mutableStateOf(0)

    val elementPairs = hashMapOf(
        Pair(1,10),
        Pair(10,60),
        Pair(60,600),
        Pair(600,3600),
        Pair(3600,36000),
        Pair(36000,60*60*24))


    fun increaseTime(multiplier : Int){
        countdownTimer += multiplier
        if (countdownTimer >= 60*60*24) countdownTimer = 60*60*24-1

    }

    fun decreaseTime(multiplier: Int){
        countdownTimer -= multiplier
        if (countdownTimer < 0) countdownTimer = 0

    }

    fun statusChanged(){
        this.started = !started
        if (this.started) startTimer()
        else startedRoutine.cancel()
    }

    private fun startTimer(){
       startedRoutine.cancel()
       startedRoutine = viewModelScope.launch(Dispatchers.Default){
            while (countdownTimer > 0){
                delay(1000)
                countdownTimer--
            }
           started = false
       }
    }

}