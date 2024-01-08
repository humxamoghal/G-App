package com.humxa.galleryapp.feature.media.presentation.navigation

sealed class Screen(val route: String) {
    data object Albums : Screen("album")
    data object AlbumDetail : Screen("album_detail")
}
