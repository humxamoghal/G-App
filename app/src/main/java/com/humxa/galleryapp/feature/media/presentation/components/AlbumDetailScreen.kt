package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.humxa.galleryapp.R
import com.humxa.galleryapp.feature.media.presentation.viewmodel.GalleryViewModel
import com.humxa.galleryapp.feature.media.presentation.view.MainActivity
import com.humxa.galleryapp.ui.theme.Black14Medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    navController: NavHostController
) {
    val viewModel: GalleryViewModel = hiltViewModel(key = MainActivity::class.java.name)
    val title = viewModel.albumName.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = title.value,
                    style = Black14Medium
                )
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            ), navigationIcon = {
                val interactionSource = remember { MutableInteractionSource() }
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource, indication = null
                        ) {
                            navController.popBackStack()
                        }
                        .padding(start = dimensionResource(id = R.dimen.margin_general_padding))
                        .size(24.dp))
            })
        }, content = { padding ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp),
                contentPadding = padding,
                content = {

                })
        }
    )

}