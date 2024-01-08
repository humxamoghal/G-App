package com.humxa.galleryapp.feature.media.domain.repository

import com.humxa.galleryapp.feature.media.domain.model.Album
import com.humxa.galleryapp.feature.media.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    fun getAlbums(): Flow<List<Album>>

    fun getMediaByAlbumId(albumId: Long): Flow<List<Media>>
}