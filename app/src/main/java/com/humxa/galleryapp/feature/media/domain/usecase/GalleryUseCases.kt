package com.humxa.galleryapp.feature.media.domain.usecase

import com.humxa.galleryapp.feature.media.domain.factory.GalleryUseCasesFactory

data class GalleryUseCases(
    val factory: GalleryUseCasesFactory
) {
    val getAlbumsUseCase = factory.createGetAlbumsUseCase()
    val getMediaUseCase = factory.createGetMediaUseCase()
    val getMediaByTypeUseCase = factory.createGetMediaByTypeUseCase()
}
