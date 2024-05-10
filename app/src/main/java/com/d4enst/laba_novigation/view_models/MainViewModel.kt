package com.d4enst.laba_novigation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d4enst.laba_novigation.db.TrackRepository
import com.d4enst.laba_novigation.MainApplication

class MainViewModel(
    private val trackRepository: TrackRepository,
) : ViewModel() {


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // контекст приложения из аргумента
                val application = (this[APPLICATION_KEY] as MainApplication)
                MainViewModel(
                    application.container.trackRepository
                )
            }
        }
    }
}