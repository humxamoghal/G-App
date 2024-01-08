package com.humxa.galleryapp.feature.media.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.humxa.galleryapp.feature.media.presentation.components.AlbumDetailScreen

fun NavGraphBuilder.albumDetailNavigation(navController: NavHostController) {
    composable(
        route = Screen.AlbumDetail.route +
                "?albumId={albumId}&albumName={albumName}",
        arguments = listOf(
            navArgument(name = "albumId") {
                type = NavType.LongType
                defaultValue = -1
            },
            navArgument(name = "albumName") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { backStackEntry ->
        val albumId = backStackEntry.arguments?.getLong("albumId") ?: -1
        val albumName = backStackEntry.arguments?.getString("albumName") ?: ""
        AlbumDetailScreen(
            navController = navController,
            albumId = albumId,
            albumName = albumName
        )
    }
}