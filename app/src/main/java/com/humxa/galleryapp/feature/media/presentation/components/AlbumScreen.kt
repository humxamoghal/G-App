package com.humxa.galleryapp.feature.media.presentation.components

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.humxa.galleryapp.core.OnResumeEffect
import com.humxa.galleryapp.feature.media.presentation.model.ScreenState
import com.humxa.galleryapp.feature.media.presentation.navigation.Screen
import com.humxa.galleryapp.feature.media.presentation.viewmodel.AlbumViewModel
import com.humxa.galleryapp.ui.theme.Dimens

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AlbumScreen(
    navController: NavHostController
) {

    val storagePermissionState = rememberMultiplePermissionsState(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                android.Manifest.permission.READ_MEDIA_VIDEO,
                android.Manifest.permission.READ_MEDIA_IMAGES
            )
        } else listOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    )

    val viewModel: AlbumViewModel = hiltViewModel(viewModelStoreOwner = LocalViewModelStoreOwner.current!!)
    val isLoading by remember(viewModel.screenState) {
        derivedStateOf {
            viewModel.screenState.value == ScreenState.LOADING
        }
    }

    val gridState = rememberLazyGridState()
    val albumSize = remember {
        Dimens.Album.size.value
    }
    val albumState = viewModel.albumsState.collectAsState()

    val isEmpty by remember(isLoading, viewModel.albumsState) {
        derivedStateOf {
            isLoading.not() && albumState.value.albums.isEmpty()
        }
    }

    OnResumeEffect {
        if (storagePermissionState.allPermissionsGranted.not()) {
            storagePermissionState.launchMultiplePermissionRequest()
        } else {
            if (isEmpty) viewModel.getAlbums()
        }
    }

    Scaffold(
        topBar = {
            TopBar(navController = navController)
        }, content = { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    state = gridState,
                    columns = GridCells.Adaptive(Dp(albumSize)),
                    contentPadding = padding,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = albumState.value.albums,
                        key = { item -> item.id }
                    ) { item ->
                        AlbumItem(
                            album = item,
                            onItemClick = { album ->
                                navController.navigate(
                                    route = Screen.AlbumDetail.route + "?albumId=${album.id}&albumName=${album.label}"
                                )
                            }
                        )
                    }
                }
                if (isLoading) {
                    LoadingScreen()
                }
                if (isEmpty) {
                    EmptyStateScreen {
                        viewModel.getAlbums()
                    }
                }
            }
        }
    )
}