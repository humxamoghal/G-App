/*
 * SPDX-FileCopyrightText: 2023 IacobIacob01
 * SPDX-License-Identifier: Apache-2.0
 */

package com.humxa.galleryapp.feature.media.data.model

import android.content.ContentResolver
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import com.humxa.galleryapp.feature.media.data.datsource.AllAlbumProvider
import com.humxa.galleryapp.feature.media.data.datsource.Query
import com.humxa.galleryapp.feature.media.domain.model.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

typealias albumsProvider = AllAlbumProvider

suspend fun ContentResolver.getAlbums(
    query: Query = Query.AlbumQuery()
): List<Album> {
    return withContext(Dispatchers.IO) {
        val albums = ArrayList<Album>()
        val bundle = query.bundle ?: Bundle()
        val albumQuery = query.copy(
            bundle = bundle.apply {
                putInt(
                    ContentResolver.QUERY_ARG_SORT_DIRECTION,
                    ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
                )
                putStringArray(
                    ContentResolver.QUERY_ARG_SORT_COLUMNS,
                    arrayOf(MediaStore.MediaColumns.DATE_MODIFIED)
                )
            },
        )
        with(query(albumQuery)) {
            moveToFirst()
            while (!isAfterLast) {
                try {
                    val id = getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_ID))
                    val label: String? = try {
                        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME))
                    } catch (e: Exception) {
                        Build.MODEL
                    }
                    val thumbnailPath =
                        getString(getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
                    val thumbnailDate =
                        getLong(getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED))
                    val album = Album(
                        id = id,
                        label = label ?: Build.MODEL,
                        pathToThumbnail = thumbnailPath,
                        timestamp = thumbnailDate,
                        count = 1
                    )
                    val currentAlbum = albums.find { it.id == id }
                    if (currentAlbum == null)
                        albums.add(album)
                    else {
                        val i = albums.indexOf(currentAlbum)
                        albums[i].count++
                        if (albums[i].timestamp <= thumbnailDate) {
                            album.count = albums[i].count
                            albums[i] = album
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                moveToNext()
            }
            close()
        }
        return@withContext albumsProvider.addAlbums(albums).sortedByDescending { it.timestamp }
    }
}


private fun ArrayList<Album>.addAllAlbums(): ArrayList<Album> {
    val time = System.currentTimeMillis()
    val allVideos = Album(
        id = -200,
        label = "All Videos",
        pathToThumbnail = this.lastOrNull()?.pathToThumbnail ?: "",
        timestamp = time,
        count = 0,
    )
    val allPhotos = Album(
        id = -201,
        label = "All Photos",
        pathToThumbnail = this.firstOrNull()?.pathToThumbnail ?: "",
        timestamp = time + 1,
        count = 0,
    )
    this.add(0, allVideos)
    this.add(0, allPhotos)
    return this
}