package de.dannyb.imnuri.data.local.entities

import androidx.room.Entity

@Entity(tableName = "hymns", primaryKeys = ["number", "title", "titleEscaped"])
data class HymnEntity(
    val number: Int,
    val title: String,
    val titleEscaped: String,
    val category: String,
    val key: String,
    val verses: List<String>,
    val hasMusicSheet: Boolean,
    val hasMp3: Boolean,
)
