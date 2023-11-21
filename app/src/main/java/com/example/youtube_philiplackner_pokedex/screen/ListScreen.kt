package com.example.youtube_philiplackner_pokedex.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.youtube_philiplackner_pokedex.screen.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun ListScreen(
    navigator: DestinationsNavigator,
) {
    Column() {

        val dominantColor = remember { 0xffffff }
        val pokemonName = remember { "random" }

        Button(

            onClick = {

                navigator.navigate(

                    DetailScreenDestination(
                        dominantColor = dominantColor,
                        pokemonName = pokemonName
                    )
                )
            }
        ) {
            Text("Click me")
        }
    }
}