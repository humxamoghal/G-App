package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import com.humxa.galleryapp.R
import com.humxa.galleryapp.feature.media.presentation.model.ScreenState
import com.humxa.galleryapp.feature.media.presentation.navigation.Screen
import com.humxa.galleryapp.feature.media.presentation.viewmodel.AlbumViewModel
import com.humxa.galleryapp.ui.theme.Black14Medium
import com.humxa.galleryapp.ui.theme.Dimens

@Composable
fun AlbumScreen(
    navController: NavHostController
) {
    val viewModel: AlbumViewModel =
        hiltViewModel(viewModelStoreOwner = LocalViewModelStoreOwner.current!!)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.title_gallery),
                style = Black14Medium
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        navigationIcon = {
            val interactionSource = remember { MutableInteractionSource() }
            Icon(painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .clickable(interactionSource = interactionSource, indication = null) {
                        navController.navigateUp()
                    }
                    .padding(start = 16.dp)
                    .size(24.dp))
        })
}