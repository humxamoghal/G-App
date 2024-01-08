package com.humxa.galleryapp.feature.media.domain.usecase

import com.humxa.galleryapp.feature.media.domain.model.Album
import com.humxa.galleryapp.feature.media.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow

class GetAlbumsUseCase(private val repository: GalleryRepository) {
    operator fun invoke(): Flow<List<Album>> = repository.getAlbums()
}
