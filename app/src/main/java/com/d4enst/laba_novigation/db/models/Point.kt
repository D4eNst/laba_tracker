package com.d4enst.laba_novigation.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "points", foreignKeys = [
    ForeignKey(
        entity = Track::class,
        parentColumns = ["id"],
        childColumns = ["track_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.RESTRICT
    )
])
data class Point(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double,

    @ColumnInfo(name = "track_id")
    var trackId: Long,
)