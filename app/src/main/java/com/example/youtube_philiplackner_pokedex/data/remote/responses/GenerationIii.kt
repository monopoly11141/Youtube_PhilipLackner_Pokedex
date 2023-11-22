package com.example.youtube_philiplackner_pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class GenerationIii(
    @SerializedName("emerald")
    val emerald: Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphire
)