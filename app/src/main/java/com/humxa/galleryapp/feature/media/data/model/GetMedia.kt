package com.humxa.galleryapp.feature.media.data.model

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.os.Build
import android.provider.MediaStore
import com.humxa.galleryapp.feature.media.data.datsource.Query
import com.humxa.galleryapp.feature.media.domain.model.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun ContentResolver.getMedia(
    mediaQuery: Query = Query.MediaQuery()
): List<Media> {
    return withContext(Dispatchers.IO) {
        val media = ArrayList<Media>()
        with(query(mediaQuery)) {
            moveToFirst()
            while (!isAfterLast) {
                try {
                    media.add(getMediaFromCursor())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                moveToNext()
            }
            close()
        }
        return@withContext media.sortedByDescending { it.timestamp }
    }
}

@Throws(Exception::class)
fun Cursor.getMediaFromCursor(): Media {
    val id: Long =
        getLong(getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
    val path: String =
        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
    val relativePath: String =
        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.RELATIVE_PATH))
    val title: String =
        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
    val albumID: Long =
        getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_ID))
    val albumLabel: String = try {
        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME))
    } catch (_: Exception) {
        Build.MODEL
    }
    val takenTimestamp: Long? = try {
        getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_TAKEN))
    } catch (_: Exception) {
        null
    }
    val modifiedTimestamp: Long =
        getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED))
    val duration: String? = try {
        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DURATION))
    } catch (_: Exception) {
        null
    }
    val mimeType: String =
        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE))
    val contentUri = if (mimeType.contains("image"))
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    else
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    val uri = ContentUris.withAppendedId(contentUri, id)
    return Media(
        id = id,
        label = title,
        uri = uri,
        path = path,
        relativePath = relativePath,
        albumID = albumID,
        albumLabel = albumLabel,
        timestamp = modifiedTimestamp,
        duration = duration,
        mimeType = mimeType
    )
}
