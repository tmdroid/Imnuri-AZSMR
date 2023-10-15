package de.dannyb.imnuri.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.dannyb.imnuri.data.local.entities.HymnEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HymnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hymns: List<HymnEntity>)

    @Query("SELECT * FROM hymns")
    suspend fun getAllHymns(): List<HymnEntity>

    @Query("SELECT COUNT(*) FROM hymns")
    fun getNumberOfHymns(): Int

}