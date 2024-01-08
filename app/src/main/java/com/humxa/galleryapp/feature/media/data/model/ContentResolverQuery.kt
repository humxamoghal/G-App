package com.humxa.galleryapp.feature.media.data.model

import android.content.ContentResolver
import android.database.Cursor
import android.database.MergeCursor
import android.provider.MediaStore
import com.humxa.galleryapp.feature.media.data.datsource.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun ContentResolver.query(
    mediaQuery: Query
): Cursor {
    return withContext(Dispatchers.IO) {
        return@withContext MergeCursor(
            arrayOf(
                query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    mediaQuery.projection,
                    mediaQuery.bundle,
                    null
                ),
                query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    mediaQuery.projection,
                    mediaQuery.bundle,
                    null
                )
            )
        )
    }
}
