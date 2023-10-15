package de.dannyb.imnuri.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HymnResponse(
    @SerializedName("imnuri") val hymns: List<HymnDto>
)
