package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.humxa.galleryapp.R
import com.humxa.galleryapp.ui.theme.Black14Medium


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumScreen(
    navController: NavHostController,
    onAlbumClick: (String) -> Unit
) {
    val gridState = rememberLazyGridState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.title_gallery),
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
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                content = {

                })
        }
    )
}