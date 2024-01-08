package com.humxa.galleryapp.feature.media.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.humxa.galleryapp.feature.media.presentation.components.AlbumScreen

fun NavGraphBuilder.albumNavigation(navController: NavHostController) {
    composable("album") {
        AlbumScreen(navController = navController) {
            navController.navigate(
                route = Screen.AlbumDetail.route
            )
        }
    }
}