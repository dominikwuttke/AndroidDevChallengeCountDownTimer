package com.example.androiddevchallenge.timerviews

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.surfaceColor
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun TopBar(){
    Surface(color = surfaceColor ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ,horizontalArrangement = Arrangement.SpaceEvenly) {
            Title(title = "Countdown Timer")
        }
    }
}

@Composable
private fun Title(title: String){
    Text(text = title,color = Color.White,style = typography.h4)
}