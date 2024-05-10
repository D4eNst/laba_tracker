package com.d4enst.laba_novigation.db

import com.d4enst.laba_novigation.db.dao.PointDao
import com.d4enst.laba_novigation.db.dao.TrackDao
import com.d4enst.laba_novigation.db.models.Point
import com.d4enst.laba_novigation.db.models.Track

class TrackRepository(
    private val trackDao: TrackDao,
    private val pointDao: PointDao
) {
    fun getAllTracks()
            = trackDao.getAll()

    fun getTrackById(trackId: Long)
            = trackDao.getById(trackId)

    fun getPointsByTrackId(trackId: Long)
            = pointDao.getByTrackId(trackId)

    suspend fun addTrack(track: Track)
            = trackDao.add(track)

    suspend fun addPoint(point: Point)
            = pointDao.add(point)

    suspend fun deleteTrack(track: Track)
            = trackDao.delete(track)

    suspend fun deletePointsByTrackId(trackId: Long)
            = pointDao.deleteByTrackId(trackId)
}