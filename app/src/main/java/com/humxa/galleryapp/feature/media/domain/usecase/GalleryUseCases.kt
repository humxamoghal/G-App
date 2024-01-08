package com.humxa.galleryapp.feature.media.domain.usecase

import com.humxa.galleryapp.feature.media.domain.repository.GalleryRepository

data class GalleryUseCases(
    val repository: GalleryRepository
) {
    val getAlbumsUseCase = GetAlbumsUseCase(repository)
    val getMediaUseCase = GetMediaUseCase(repository)
}
