package com.example.youtube_philiplackner_pokedex.repository

import com.example.youtube_philiplackner_pokedex.data.remote.PokeApi
import com.example.youtube_philiplackner_pokedex.data.remote.responses.Pokemon
import com.example.youtube_philiplackner_pokedex.data.remote.responses.PokemonList
import com.example.youtube_philiplackner_pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val pokeApi : PokeApi
){
    suspend fun getPokemonList(limit : Int, offset : Int) : Resource<PokemonList> {
        val response = try {
            pokeApi.getPokemonList(limit, offset)
        }
        catch (e : Exception) {
            return Resource.Error("An unknown error occurred.")
        }

        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName : String) : Resource<Pokemon> {
        val response = try {
            pokeApi.getPokemonInfo(pokemonName)
        }
        catch (e : Exception) {
            return Resource.Error("An unknown error occurred.")
        }

        return Resource.Success(response)
    }

}