package com.dflch.wheater


import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WheaterScreen()
        }

        //Hide status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = true

        //window.decorView.systemUiVisibility = (
        //        window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        //        )
    }
}
@Preview
@Composable
fun WheaterScreen() {
    Box (
      modifier = Modifier
          .fillMaxSize()
          .background(
              brush = Brush.horizontalGradient(
                  colors = listOf(
                      Color(android.graphics.Color.parseColor("#59469d")),
                      Color(android.graphics.Color.parseColor("#643d67")),
                  )
              )
          )
    ){
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        "Mostly Cloudy",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 48.dp),
                        textAlign = TextAlign.Center
                    )

                    Image(painterResource(id = R.drawable.cloudy_sunny),
                        contentDescription = "Null",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(top = 18.dp)

                    )

                    //Display date and time
                    Text(
                        "Mon, November 30 | 10:00 AM",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )

                    //Display temperature
                    Text(
                        "25°",
                        fontSize = 63.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        "H:27 L:18",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )

                    //Box containing weather details like rain, wind speed, humidity
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .background(
                                color = colorResource(id = R.color.purple),
                                shape = RoundedCornerShape(25.dp)
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            WeatherDetailItem(icon = R.drawable.rain, value = "22%", label = "Rain" )
                            WeatherDetailItem(icon = R.drawable.wind, value = "22 Km/h", label = "Wind Speed" )
                            WeatherDetailItem(icon = R.drawable.humidity, value = "22%", label = "Humidity" )
                        }
                    }

                    //Displaying "Today" label
                    Text(
                        "Today",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 8.dp),
                    )
                }

                //Display future hourly forescast using a lazyRow
                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        items(items) { item ->
                            FutureModelViewHolder(item)
                        }
                    }
                }

                //Sample daily data
                val dailyItems = listOf(
                    FutureModel("Sat", "storm", "Storm", 27, 12),
                    FutureModel("Sun", "cloudy", "Cloudy", 24, 16),
                    FutureModel("Mon", "windy", "Windy", 29, 15),
                    FutureModel("Tue", "cloudy_sunny", "Cloudy Sunny", 28, 15),
                    FutureModel("Wen", "sunny", "Sunny", 27, 11),
                    FutureModel("Thu", "rainy", "Rainy", 29, 12),
                    FutureModel("Fri", "windy", "Windy", 30, 18),
                )


                //Display "Future" label and next 7 day button
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "Future",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            "Next 7 days >",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                items(dailyItems) { item ->
                    FutureItem(item = item)
                }
            }
        }
    }
}

//Display each future daily forecast item
@Composable
fun FutureItem(
    item: FutureModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.day,
            fontSize = 14.sp,
            color = Color.White
        )

        Image(
            painter = painterResource(id = getDrawableResourseId(picPath = item.picPath)),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 32.dp)
                .size(45.dp)
        )

        Text(
            text = item.status,
            modifier = Modifier
                .weight(1f)
                .padding(start = 32.dp),
            fontSize = 14.sp,
            color = Color.White
        )

        Text(
            text = "${item.highTemp}°",
            modifier = Modifier
                .padding(end = 16.dp),
            fontSize = 14.sp,
            color = Color.White
        )

        Text(
            text = "${item.lowTemp}°",
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Composable
fun getDrawableResourseId(picPath: String): Int {
    return when (picPath) {
        "storm" -> R.drawable.storm
        "cloudy" -> R.drawable.cloudy
        "windy" -> R.drawable.windy
        "cloudy_sunny" -> R.drawable.cloudy_sunny
        "sunny" -> R.drawable.sunny
        else -> R.drawable.cloudy_sunny
    }
}

//sample hourly data
val items = listOf(
    HourlyModel("9 p.m", 28, "cloudy"),
    HourlyModel("10 p.m", 29, "sunny"),
    HourlyModel("11 p.m", 30, "wind"),
    HourlyModel("12 p.m", 31, "rainy"),
    HourlyModel("1 a.m", 28, "storm"),
)

//Viewholder for each hourly forecast item
@Composable
fun FutureModelViewHolder(hourlyModel: HourlyModel) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.purple),
                shape = RoundedCornerShape(25.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hourlyModel.hour,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "${hourlyModel.temp}°",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

        Image(painter = painterResource(id = when(hourlyModel.picPath){
            "cloudy" -> R.drawable.cloudy
            "sunny" -> R.drawable.sunny
            "wind" -> R.drawable.wind
            "rainy" -> R.drawable.rain
            "storm" -> R.drawable.storm
            else -> R.drawable.sunny
        } ),
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun WeatherDetailItem(
    icon: Int,
    value: String,
    label: String
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)
        )

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center
        )

        Text(
            text = label,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center
        )

    }
}