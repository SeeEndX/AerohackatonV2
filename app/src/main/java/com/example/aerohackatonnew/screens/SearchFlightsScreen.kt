package com.example.aerohackatonnew.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


data class Flight(val id: Int, val flightName: String, val airplaneName : String)

object FlightList {
    val flights = listOf(
        Flight(1, "S4242", "Airbus A320"),
        Flight(2, "A1234", "Airbus B-737"),
        Flight(3, "flight3", "Boeing 737-800"),
        Flight(4, "B4321", "Boeing 777"),
        Flight(5, "flight5", "ну самолет"),
        Flight(6, "polet6", "летит"),
        Flight(7, "poletel", "улетит"),
        Flight(8, "flight8", "полетит"),
        Flight(9, "flight9", "вылетит"),
        Flight(10, "flight10", "подлетит"),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFlightsScreen(navHostController: NavHostController) {
    var query by remember { mutableStateOf("") }

    val filteredAircraftList = FlightList.flights.filter {
        it.flightName.contains(query, ignoreCase = true)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            shape = MaterialTheme.shapes.medium
        )
        {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(2.dp),
                    tint = Color.Blue
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = query,
                    onValueChange = {
                        query = it
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                        }
                    ),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    placeholder = {
                        Text(
                            text = "Поиск по номеру рейса",
                            style = TextStyle(
                                fontSize = 18.sp
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
        ) {
            items(filteredAircraftList) { flight ->
                FlightCard(flight, navHostController)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun FlightCard(flight: Flight, navController : NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = true
            ) {
                //логика клика - переход на схему
                navController.navigate("flight/${flight.flightName}")
            },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = flight.flightName,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
