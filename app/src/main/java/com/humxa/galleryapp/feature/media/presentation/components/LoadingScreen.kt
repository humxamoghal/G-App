package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.pointerInput(Unit) { }) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}