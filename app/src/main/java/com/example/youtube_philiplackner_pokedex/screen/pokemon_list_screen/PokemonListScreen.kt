package com.example.youtube_philiplackner_pokedex.screen.pokemon_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.youtube_philiplackner_pokedex.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun PokemonListScreen(
    navigator: DestinationsNavigator,
) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Image(
                painter = painterResource(R.drawable.ic_international_pok_mon_logo),
                contentDescription = "Pokemon logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                hint = "Search...",
            ) {

            }

        }
    }

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint.isNotEmpty()) }
    val focusRequester = FocusRequester()
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 5.dp,
                    shape = CircleShape
                )
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 12.dp
                )
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                }
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 12.dp
                    )
            )
        }
    }


}

//    Column() {
//
//        val dominantColor = remember { 0xffffff }
//        val pokemonName = remember { "random" }
//
//        Button(
//
//            onClick = {
//
//                navigator.navigate(
//
//                    DetailScreenDestination(
//                        dominantColor = dominantColor,
//                        pokemonName = pokemonName
//                    )
//                )
//            }
//        ) {
//            Text("Click me")
//        }
//    }
