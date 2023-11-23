package com.example.youtube_philiplackner_pokedex.screen.pokemon_list_screen

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.youtube_philiplackner_pokedex.data.model.PokedexListEntry
import com.example.youtube_philiplackner_pokedex.repository.PokemonRepository
import com.example.youtube_philiplackner_pokedex.util.Constant
import com.example.youtube_philiplackner_pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListScreenViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(emptyList())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var isEndReached = mutableStateOf(false)

    fun loadPokemonPaginated() {
        isLoading.value = true

        viewModelScope.launch {
            val result = repository.getPokemonList(Constant.PAGE_SIZE, currentPage * Constant.PAGE_SIZE)

            when (result) {
                is Resource.Success -> {
//                    isEndReached.value = currentPage * Constant.PAGE_SIZE >= result.data!!.count
                    val pokedexEntries = result.data!!.results.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }

                        val url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexListEntry(
                            entry.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                            url,
                            number.toInt()
                        )
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexEntries
                }

                is Resource.Error -> {
//                    loadError.value = result.message!!
//                    isLoading.value = false
                }
            }
        }
    }


    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}