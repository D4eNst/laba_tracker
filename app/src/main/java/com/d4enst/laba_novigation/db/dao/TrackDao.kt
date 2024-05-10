package com.d4enst.laba_novigation.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.d4enst.laba_novigation.db.models.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert(Track::class)
    suspend fun add(track: Track): Long

    @Delete(Track::class)
    suspend fun delete(track: Track)

    @Query("SELECT * FROM tracks")
    fun getAll(): Flow<List<Track>>

    @Query("SELECT * FROM tracks WHERE id = :id")
    fun getById(id: Long): Flow<Track?>
}