package com.humxa.galleryapp.feature.media.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.humxa.galleryapp.feature.media.presentation.components.AlbumScreen
import com.humxa.galleryapp.feature.media.presentation.viewmodel.AlbumViewModel

fun NavGraphBuilder.albumNavigation(navController: NavHostController) {
    composable(Screen.Albums.route) {
        val viewModel: AlbumViewModel =
            hiltViewModel(viewModelStoreOwner = LocalViewModelStoreOwner.current!!)
        val albumState = viewModel.albumsState.collectAsState()
        AlbumScreen(
            navController = navController,
            screenState = viewModel.screenState.value,
            albumState = albumState.value,
            getAlbums = viewModel::getAlbums
        )
    }
}