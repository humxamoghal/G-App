package com.humxa.galleryapp.feature.media.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Albums.route,
    ) {
        albumNavigation(navController = navController)
        albumDetailNavigation(navController = navController)
    }
}