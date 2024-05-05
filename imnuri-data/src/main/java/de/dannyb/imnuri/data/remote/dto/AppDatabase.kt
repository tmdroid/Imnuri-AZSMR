package de.dannyb.imnuri.data.remote.dto

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.dannyb.imnuri.data.local.HymnDao
import de.dannyb.imnuri.data.local.entities.HymnEntity
import de.dannyb.imnuri.data.utils.Converters

@Database(entities = [HymnEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hymnDao(): HymnDao
}