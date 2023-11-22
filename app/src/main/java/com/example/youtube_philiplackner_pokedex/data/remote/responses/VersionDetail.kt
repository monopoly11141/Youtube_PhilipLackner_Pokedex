package com.example.youtube_philiplackner_pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    @SerializedName("rarity")
    val rarity: Int,
    @SerializedName("version")
    val version: Version
)