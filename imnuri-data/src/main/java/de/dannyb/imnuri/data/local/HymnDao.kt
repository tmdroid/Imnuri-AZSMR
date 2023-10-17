package de.dannyb.imnuri.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.dannyb.imnuri.data.local.entities.HymnEntity

@Dao
interface HymnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hymns: List<HymnEntity>)

    @Query("SELECT * FROM hymns")
    suspend fun getAllHymns(): List<HymnEntity>

    @Query("SELECT COUNT(*) FROM hymns")
    fun getNumberOfHymns(): Int

    @Query("SELECT * FROM hymns WHERE CAST(number AS TEXT) LIKE :prefix || '%'")
    fun searchHymnsByNumber(prefix: Int): List<HymnEntity>

    @Query("SELECT * FROM hymns WHERE title LIKE '%' || :title || '%' OR titleEscaped LIKE '%' || :title || '%'")
    fun searchHymnsByTitle(title: String): List<HymnEntity>

    @Query("SELECT * FROM hymns WHERE number = :number")
    fun getHymnByNumber(number: Int): HymnEntity

}