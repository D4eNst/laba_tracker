package com.d4enst.laba_novigation.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.d4enst.laba_novigation.db.models.Point
import kotlinx.coroutines.flow.Flow

@Dao
interface PointDao {
    @Insert(Point::class)
    suspend fun add(point: Point): Long

    @Query("DELETE FROM points WHERE track_id = :crackId")
    suspend fun deleteByTrackId(crackId: Long)

    @Query("SELECT * FROM points WHERE track_id = :id ORDER BY timestamp")
    fun getByTrackId(id: Long): Flow<List<Point>>
}