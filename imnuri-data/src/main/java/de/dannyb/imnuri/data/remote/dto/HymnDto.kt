package de.dannyb.imnuri.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HymnDto(
    @SerializedName("mp3") var hasMp3: Boolean,
    @SerializedName("numar") var number: Int,
    @SerializedName("categorie") var category: String,
    @SerializedName("strofe") var verses: ArrayList<String> = arrayListOf(),
    @SerializedName("sheet") var hasMusicSheet: Boolean,
    @SerializedName("titlu") var title: String,
    @SerializedName("gama") var key: String
)