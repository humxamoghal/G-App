package com.humxa.galleryapp.feature.media.domain.repository

import com.humxa.galleryapp.feature.media.domain.model.Album
import com.humxa.galleryapp.feature.media.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    suspend fun getAlbums(): Flow<List<Album>>

    suspend fun getMediaByAlbumId(albumId: Long): Flow<List<Media>>
}