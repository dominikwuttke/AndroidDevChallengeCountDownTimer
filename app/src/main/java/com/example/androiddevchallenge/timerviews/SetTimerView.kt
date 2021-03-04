package com.example.androiddevchallenge.timerviews

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.TimerElements
import com.example.androiddevchallenge.TimerViewModel
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.typography

@ExperimentalAnimationApi
@Composable
fun SetTimerView(){
    val viewModel = viewModel<TimerViewModel>()
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        val timerView = createRef()
        Row(modifier = Modifier
            .constrainAs(timerView) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly,verticalAlignment = Alignment.CenterVertically) {
           if (!(viewModel.started && (viewModel.countdownTimer < 3600))) Row(verticalAlignment = Alignment.CenterVertically) {
               TimerColumn(multiplier = 36000, viewModel = viewModel)
               TimerColumn(multiplier = 3600, viewModel = viewModel)

           }
            if (!(viewModel.started && (viewModel.countdownTimer < 3600))) Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = ":")
            }
            if (!(viewModel.started && (viewModel.countdownTimer < 60)))Row(verticalAlignment = Alignment.CenterVertically) {
                TimerColumn(multiplier = 600, viewModel = viewModel)
                TimerColumn(multiplier = 60, viewModel = viewModel)

            }
            if (!(viewModel.started && (viewModel.countdownTimer < 60))) Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = ":")
            }
            Row() {
                TimerColumn(multiplier = 10, viewModel = viewModel)
                TimerColumn(multiplier = 1, viewModel = viewModel)
            }







        }
    }




}

@ExperimentalAnimationApi
@Composable
private fun TimerColumn(multiplier : Int,viewModel: TimerViewModel){

        Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(8.dp),verticalArrangement = Arrangement.Center) {
            AnimatedVisibility(visible = !viewModel.started,) {
                IncreaseTimer(multiplier = multiplier, viewModel = viewModel)
            }

            TimerDisplay(viewModel = viewModel, multiplier = multiplier)

            AnimatedVisibility(visible = !viewModel.started,) {
                DecreaseTimer(multiplier = multiplier, viewModel = viewModel)
            }
        }


    //Max Value für dieses Element
    //Untergeordnetes Element
    //Übergeordnetes Element
}

@Composable
private fun IncreaseTimer(multiplier : Int,viewModel: TimerViewModel){
    Image(painter =  painterResource(id = R.drawable.add_24), contentDescription = "increase",
        modifier = Modifier
            .clickable { viewModel.increaseTime(multiplier) }
            .padding(8.dp))
    //Button(onClick = { viewModel.increaseTime(multiplier) }) {

   // }
    //Maximalwert
}

@Composable
private fun DecreaseTimer(multiplier : Int,viewModel: TimerViewModel){

    Image(painter =  painterResource(id = R.drawable.minus_24), contentDescription = "decrease",
    modifier = Modifier
        .clickable { viewModel.decreaseTime(multiplier) }
        .padding(8.dp))
    //Image(imageVector = ImageVector(), contentDescription = )
   // Button(onClick = { viewModel.decreaseTime(multiplier) }) {
   // }
}

@Composable
private fun TimerDisplay( multiplier: Int,viewModel: TimerViewModel){
    val number = evaluateNumber(viewModel.elementPairs.getValue(multiplier),multiplier,viewModel.countdownTimer)
    if (viewModel.encoded) Image(painter =  encodeNumber(number = number), contentDescription = "Number",modifier = Modifier
        .width(44.dp)
        .height(44.dp))
    else Text(text = number.toString(),textAlign = TextAlign.Center,modifier = Modifier
        .width(44.dp)
        .height(44.dp),style = typography.body2)
}

private fun evaluateNumber(higherValue : Int,multiplier: Int,value : Int): Int{
    return (value % higherValue) / multiplier

}

@Composable
private fun encodeNumber(number : Int) : Painter{
    val imageID = when(number){
        0->R.drawable.number0
        1->R.drawable.number1
        2->R.drawable.number2
        3->R.drawable.number3
        4->R.drawable.number4
        5->R.drawable.number5
        6->R.drawable.number6
        7->R.drawable.number7
        8->R.drawable.number8
        else->R.drawable.number9
    }
    return painterResource(imageID)
}


