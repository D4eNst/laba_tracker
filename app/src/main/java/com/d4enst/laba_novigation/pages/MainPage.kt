package com.d4enst.laba_novigation.pages

import androidx.compose.foundation.layout.fillMaxSize
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    mvm: MainViewModel,
) {
    mvm.startLocationUpdates(LocalContext.current)

}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
    MainPage(
        navController,
        Modifier
            .fillMaxSize(),
        mainViewModel
    )
}