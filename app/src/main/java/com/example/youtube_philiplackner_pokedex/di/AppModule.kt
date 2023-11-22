package com.example.youtube_philiplackner_pokedex.di

import com.example.youtube_philiplackner_pokedex.data.remote.PokeApi
import com.example.youtube_philiplackner_pokedex.repository.PokemonRepository
import com.example.youtube_philiplackner_pokedex.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(pokeApi: PokeApi) = PokemonRepository(pokeApi)

    @Singleton
    @Provides
    fun providePokeApi(): PokeApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
            .create(PokeApi::class.java)
    }

}