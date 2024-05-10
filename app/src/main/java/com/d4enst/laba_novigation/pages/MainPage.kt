package com.d4enst.laba_novigation.pages

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Text("Hello, my app!")
}