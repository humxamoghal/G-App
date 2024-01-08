package com.humxa.galleryapp.feature.media.domain.factory

import com.humxa.galleryapp.feature.media.domain.repository.GalleryRepository
import com.humxa.galleryapp.feature.media.domain.usecase.GetAlbumsUseCase
import com.humxa.galleryapp.feature.media.domain.usecase.GetMediaByTypeUseCase
import com.humxa.galleryapp.feature.media.domain.usecase.GetMediaUseCase

class GalleryUseCasesFactoryImpl(private val repository: GalleryRepository) :
    GalleryUseCasesFactory {
    override fun createGetAlbumsUseCase(): GetAlbumsUseCase {
        return GetAlbumsUseCase(repository)
    }

    override fun createGetMediaUseCase(): GetMediaUseCase {
        return GetMediaUseCase(repository)
    }

    override fun createGetMediaByTypeUseCase(): GetMediaByTypeUseCase {
        return GetMediaByTypeUseCase(repository)
    }
}