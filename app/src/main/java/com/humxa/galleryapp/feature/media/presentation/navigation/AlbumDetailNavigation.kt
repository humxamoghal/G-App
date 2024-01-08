package com.humxa.galleryapp.feature.media.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.humxa.galleryapp.feature.media.presentation.components.AlbumDetailScreen

fun NavGraphBuilder.albumDetailNavigation(navController: NavHostController) {
    composable(Screen.AlbumDetail.route) {
        AlbumDetailScreen(navController = navController)
    }
}