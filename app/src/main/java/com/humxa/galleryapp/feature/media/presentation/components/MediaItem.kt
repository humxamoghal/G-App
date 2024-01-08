package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.humxa.galleryapp.R
import com.humxa.galleryapp.feature.media.domain.model.Media
import com.humxa.galleryapp.ui.theme.Dimens

@Composable
fun MediaItem(
    media: Media,
    onItemClick: (Media) -> Unit,
) {
    val mediaSize = remember { Dimens.Photo.size.value }
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .size(mediaSize.dp)
    ) {
        MediaImage(
            media = media,
            onItemClick = onItemClick,
        )

        if (media.isVideo) {
            Image(
                painter = painterResource(id = R.drawable.ic_play_button),
                contentDescription = "Play Button",
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
            )
        }

    }

}