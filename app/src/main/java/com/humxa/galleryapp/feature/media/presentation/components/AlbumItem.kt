package com.humxa.galleryapp.feature.media.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.humxa.galleryapp.R
import com.humxa.galleryapp.feature.media.domain.model.Album
import com.humxa.galleryapp.feature.media.presentation.widget.AutoResizeText
import com.humxa.galleryapp.feature.media.presentation.widget.FontSizeRange
import com.humxa.galleryapp.ui.theme.Dimens

@Composable
fun AlbumItem(
    album: Album,
    onItemClick: (Album) -> Unit,
) {
    val albumSize = remember { Dimens.Album.size.value }
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .size(albumSize.dp)
        ) {
            AlbumImage(
                album = album,
                onItemClick = onItemClick,
            )
        }
        AutoResizeText(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(horizontal = 16.dp),
            text = album.label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontSizeRange = FontSizeRange(
                min = 10.sp,
                max = 16.sp
            )
        )
        if (album.count > 0) {
            AutoResizeText(
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                text = pluralStringResource(
                    id = R.plurals.item_count,
                    count = album.count.toInt(),
                    album.count
                ),
                style = MaterialTheme.typography.labelMedium,
                fontSizeRange = FontSizeRange(
                    min = 6.sp,
                    max = 12.sp
                )
            )
        }

    }
}
