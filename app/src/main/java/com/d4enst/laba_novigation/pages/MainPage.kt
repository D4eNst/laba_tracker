package com.d4enst.laba_novigation.pages

import androidx.compose.foundation.layout.fillMaxSize
import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.d4enst.laba_novigation.R
import com.d4enst.laba_novigation.view_models.MainViewModel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Locale

import androidx.compose.ui.viewinterop.AndroidView

import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Circle
import com.yandex.mapkit.geometry.Geometry
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polyline
import com.yandex.mapkit.mapview.MapView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    mvm: MainViewModel,
) {
    mvm.startLocationUpdates(LocalContext.current)
    YaMap(locations = mvm.locations, modifier = Modifier.fillMaxSize())
}

@Composable
fun YaMap(
    locations: List<Location>,
    modifier: Modifier = Modifier,
){
    Box(modifier = modifier){
        AndroidView(
            factory = {
                MapView(it)
            },
            update = { mapView ->
                mapView.mapWindow.map.apply {
                    mapObjects.clear()
                    locations.let {
                        //val c = Circle(Point(it.latitude, it.longitude), 10f)
                        //val c10 = Circle(Point(it.latitude, it.longitude), 100f)
                        if (locations.isNotEmpty()) {
                            val pl = Polyline(locations.map { Point(it.latitude, it.longitude) })
                            cameraPosition(Geometry.fromPolyline(pl)).let {
                                move(it)
                            }
                            //mapObjects.addCircle(c)
                            mapObjects.addPolyline(pl)
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    YaMap(locations = emptyList())
}