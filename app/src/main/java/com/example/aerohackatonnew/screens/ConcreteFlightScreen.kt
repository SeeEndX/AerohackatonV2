package com.example.aerohackatonnew.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//конкретный экран борта и тд
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConcreteFlightScreen(flight: Flight?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        if (flight != null) {
            TopAppBar(
                title = {
                    Text(
                        text = "Номер борта: ${flight.airplaneNumber}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                },
                //тут бы поменять цвета, но это смотрите сами
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
            Text(
                text = "Рейс: ${flight.flightName}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Самолет: ${flight.airplaneName}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            // разметка схемы самолета
            AircraftSeatLayout(32,4)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // функция создания отчета
                changeCell()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        ) {
            Text(text = "Создать отчет")
        }
    }
}

var cellColor = Color.Gray

//разметка сидений
@Composable
fun AircraftSeatLayout(econSeatsCount: Int, businSeatsCount: Int) {
    //для символов, проще так было
    val economClass = listOf("A", "B", "C", "1", "D", "E", "F")
    val businessClass = listOf("A", "B", "1", "D", "E")

    LazyColumn(
        contentPadding = PaddingValues(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item{
            Text(
                text = "Нос самолета",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Бизнес-класс",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        items(businSeatsCount) { rowIndex ->
            val number = rowIndex + 1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                businessClass.forEach { char ->
                    if (char.length == 1 && char[0].isLetter()) {
                        Box(
                            modifier = Modifier
                                .background(cellColor)
                                .padding(4.dp)
                                .size(36.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(char, modifier = Modifier.padding(4.dp))
                        }
                    } else {
                        Text("$number", modifier = Modifier.padding(6.dp))
                    }
                }
            }
        }
        item{
            Divider(
                color = Color.Black,
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Эконом-класс",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        items(econSeatsCount) { rowIndex ->
            val number = businSeatsCount + 1 + rowIndex
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                economClass.forEach { char ->
                    if (char.length == 1 && char[0].isLetter()) {
                        Box(
                            modifier = Modifier
                                .background(cellColor)
                                .padding(4.dp)
                                .size(36.dp)
                                .clickable{

                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(char, modifier = Modifier.padding(4.dp))
                        }
                    } else {
                        Text("$number", modifier = Modifier.padding(6.dp))
                    }
                }
            }
        }
        item{
            Text(
                text = "Хвост самолета",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun changeCell(){
    //тут пропишите логику смены цвета, щас по нажатию на генерацию отчета меняется
    //обновление при выходе или пролистывании
    //в реальном времени уже нет сил прописывать, надеюсь, справитесь, удачи)
    cellColor = Color.Green
}

fun generateDocument(){
    //генерация отчета
}