package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.humxa.galleryapp.feature.media.data.datsource.DEFAULT_PHOTOS_ALBUM_ID
import com.humxa.galleryapp.feature.media.data.datsource.DEFAULT_VIDEOS_ALBUM_ID
import com.humxa.galleryapp.feature.media.presentation.model.MediaType
import com.humxa.galleryapp.feature.media.presentation.model.ScreenState
import com.humxa.galleryapp.feature.media.presentation.viewmodel.MediaViewModel
import com.humxa.galleryapp.ui.theme.Dimens

@Composable
fun AlbumDetailScreen(
    navController: NavHostController,
    albumId: Long,
    albumName: String
) {
    val viewModel: MediaViewModel = hiltViewModel()
    val mediaState = viewModel.mediaState.collectAsState()
    val gridState = rememberLazyGridState()
    val mediaSize = remember {
        Dimens.Photo.size.value
    }
    val isLoading by remember(viewModel.screenState) {
        derivedStateOf {
            viewModel.screenState.value == ScreenState.LOADING
        }
    }
    LaunchedEffect(true) {

        when (albumId) {
            DEFAULT_PHOTOS_ALBUM_ID -> {
                viewModel.getMedia(MediaType.PHOTOS)
            }

            DEFAULT_VIDEOS_ALBUM_ID -> {
                viewModel.getMedia(MediaType.VIDEOS)
            }

            else -> {
                viewModel.getMedia(albumId)
            }
        }
    }
    Scaffold(
        topBar = {
            TopBar(navController = navController, title = albumName)
        }, content = { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    state = gridState,
                    columns = GridCells.Adaptive(Dp(mediaSize)),
                    contentPadding = padding,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(
                        items = mediaState.value.media,
                        key = { item -> item.label }
                    ) { media ->
                        MediaItem(media = media, onItemClick = { })
                    }
                }
                if (isLoading) {
                    LoadingScreen()
                }
            }
        }
    )

}