package com.humxa.galleryapp.feature.media.domain.usecase

import com.humxa.galleryapp.feature.media.domain.model.Media
import com.humxa.galleryapp.feature.media.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow

class GetMediaUseCase(private val repository: GalleryRepository) {
    operator fun invoke(albumId: Long): Flow<List<Media>> = repository.getMediaByAlbumId(albumId)
}