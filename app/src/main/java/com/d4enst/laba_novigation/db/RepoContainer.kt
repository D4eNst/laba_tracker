package com.d4enst.laba_novigation.db

import android.content.Context

class RepoContainer(private val context: Context) {
    val trackRepository: TrackRepository by lazy {
        TrackRepository(
            Database.getDatabase(context).getTrackDao(),
            Database.getDatabase(context).getPointDao()
        )
    }
}