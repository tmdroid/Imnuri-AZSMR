package de.dannyb.imnuri.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hymns", primaryKeys = ["number", "title"])
data class HymnEntity(
    @PrimaryKey val number: Int,
    val title: String,
    val category: String,
    val key: String,
    val verses: ArrayList<String>,
    val hasMusicSheet: Boolean,
    val hasMp3: Boolean,
)
