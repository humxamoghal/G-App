package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.humxa.galleryapp.R
import com.humxa.galleryapp.ui.theme.Black14Medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    title: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
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