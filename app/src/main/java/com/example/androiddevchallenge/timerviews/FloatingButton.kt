package com.example.androiddevchallenge.timerviews

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.TimerViewModel

@Composable
fun FloatingButton(viewModel : TimerViewModel){


    FloatingActionButton(onClick = { viewModel.statusChanged()}) {
        Text(text = if (viewModel.started) "STOP" else "START")
    }
}