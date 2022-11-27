package com.moncrieff.tirelesstracker

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.moncrieff.tirelesstracker.ui.theme.TirelessTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Keep the screen on whilst tracking life totals
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        setContent {
            TirelessTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ){
                        LifeBox(
                            fillHeight = 0.5f,
                            rotation = 180f)
                        LifeBox()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun LifeBox (
    fillHeight: Float = 1f,
    rotation: Float = 0f
) {
    val painter: Painter = painterResource(id = R.drawable.primeval)
    val lifeTotal = remember {
        mutableStateOf(20)
    }
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fillHeight)
            .rotate(rotation),
    ) {
        //Background Image
        Image(
            painter = painter ,
            contentDescription = "",
            contentScale = ContentScale.Crop)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.hsv(0f, 0f, 0f, 0.2f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = lifeTotal.value.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 100.sp
                )
        }
            //Tracker buttons
            Column() {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .fillMaxWidth()
                        .clickable {
                            lifeTotal.value = lifeTotal.value + 1
                        }
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .clickable {
                            lifeTotal.value = lifeTotal.value - 1
                        }
                )
            }
        }
}

@Composable
fun OptionBar (
    fillHeight: Float
) {
    //We want the bar to be able to reset totals and roll the starting dice
    Box() {
        Row() {
            Box(
               modifier = Modifier
                   .fillMaxWidth(0.5f)
                   .clickable {

                   }

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TirelessTrackerTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ){
                LifeBox(
                    fillHeight = 0.5f,
                    rotation = 180f)
                LifeBox()
            }
        }
    }
}