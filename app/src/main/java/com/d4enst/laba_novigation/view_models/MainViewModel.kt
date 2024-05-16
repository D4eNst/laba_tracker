package com.d4enst.laba_novigation.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import android.location.Location
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d4enst.laba_novigation.db.TrackRepository
import com.d4enst.laba_novigation.MainApplication
import com.d4enst.laba_novigation.location.Locator
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager

import android.os.Looper
import androidx.compose.runtime.mutableStateListOf
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class MainViewModel(
    private val trackRepository: TrackRepository,
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            Locator.location.collect{ loc ->
                withContext(Dispatchers.Main){
                    loc?.let{ locations.add(it) }
                }
            }
        }
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
//  Нужно инициализировать перед использованием локации (с использованием контекста активности)
    fun initializeFusedLocationClient(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    val locations = mutableStateListOf<Location>()

    fun startLocationUpdates(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient?.let{
            it.lastLocation.addOnCompleteListener {
                viewModelScope.launch {
                    fusedLocationClient.requestLocationUpdates(
                        Locator.locationRequest,
                        Locator,
                        Looper.getMainLooper()
                    )
                }
            }
        }

    }

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