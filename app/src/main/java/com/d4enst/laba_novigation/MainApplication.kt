package com.d4enst.laba_novigation

import android.app.Application
import com.d4enst.laba_novigation.db.RepoContainer


class MainApplication : Application() {
    lateinit var container: RepoContainer

    override  fun onCreate() {
        super.onCreate()
        container = RepoContainer(this)
    }
}
