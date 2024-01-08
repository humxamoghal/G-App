package com.humxa.galleryapp.feature.media.data.repository

import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import com.humxa.galleryapp.core.contentFlowObserver
import com.humxa.galleryapp.feature.media.data.datsource.Query
import com.humxa.galleryapp.feature.media.data.model.getAlbums
import com.humxa.galleryapp.feature.media.data.model.getMedia
import com.humxa.galleryapp.feature.media.domain.model.Album
import com.humxa.galleryapp.feature.media.domain.model.Media
import com.humxa.galleryapp.feature.media.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.map

class GalleryRepositoryImpl(private val context: Context) : GalleryRepository {

    companion object {
        private val URIs = arrayOf(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
    }

    override suspend fun getAlbums(): Flow<List<Album>> {
        return context.retrieveAlbums {
            it.getAlbums().toMutableList()
        }
    }

    override suspend fun getMediaByAlbumId(albumId: Long): Flow<List<Media>> {
        return context.retrieveMedia {
            val query = Query.MediaQuery().copy(
                bundle = Bundle().apply {
                    putString(
                        ContentResolver.QUERY_ARG_SQL_SELECTION,
                        MediaStore.MediaColumns.BUCKET_ID + "= ?"
                    )
                    putStringArray(
                        ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS,
                        arrayOf(albumId.toString())
                    )
                }
            )
            it.getMedia(query)
        }
    }

    private fun Context.retrieveMedia(dataBody: suspend (ContentResolver) -> List<Media>) =
        contentFlowObserver(URIs).map {
            try {
                dataBody.invoke(contentResolver)
            } catch (e: Exception) {
                emptyList()
            }
        }.conflate()

    private fun Context.retrieveAlbums(dataBody: suspend (ContentResolver) -> List<Album>) =
        contentFlowObserver(URIs).map {
            try {
                dataBody.invoke(contentResolver)
            } catch (e: Exception) {
                emptyList()
            }
        }.conflate()

}