package com.d4enst.laba_novigation.db

import android.content.Context
import androidx.room.Room
import androidx.room.Database as DatabaseAnnotation
import androidx.room.RoomDatabase
import com.d4enst.laba_novigation.db.dao.PointDao
import com.d4enst.laba_novigation.db.dao.TrackDao
import com.d4enst.laba_novigation.db.models.Point
import com.d4enst.laba_novigation.db.models.Track

@DatabaseAnnotation(
    [Track::class, Point::class],
    version = 1,
    exportSchema = false,
)
abstract class Database : RoomDatabase(){
    abstract fun getTrackDao(): TrackDao
    abstract fun getPointDao(): PointDao

    companion object {
        @Volatile
        private var Instance: Database? = null

        fun getDatabase(context: Context): Database {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = Database::class.java,
                    name = "database"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}