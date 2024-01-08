@file:OptIn(ExperimentalGlideComposeApi::class)

package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.humxa.galleryapp.feature.media.domain.model.Album
import java.io.File

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun AlbumImage(
    album: Album,
    isEnabled: Boolean,
    onItemClick: (Album) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val radius = if (isPressed.value) 32.dp else 16.dp
    val cornerRadius by animateDpAsState(targetValue = radius, label = "cornerRadius")
    GlideImage(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .combinedClickable(
                enabled = isEnabled,
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = { onItemClick(album) },
            ),
        model = File(album.pathToThumbnail),
        contentDescription = album.label,
        contentScale = ContentScale.Crop
    )
}