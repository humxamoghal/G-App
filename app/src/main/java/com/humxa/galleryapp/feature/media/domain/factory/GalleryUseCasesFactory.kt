package com.humxa.galleryapp.feature.media.domain.factory

import com.humxa.galleryapp.feature.media.domain.usecase.GetAlbumsUseCase
import com.humxa.galleryapp.feature.media.domain.usecase.GetMediaByTypeUseCase
import com.humxa.galleryapp.feature.media.domain.usecase.GetMediaUseCase

interface GalleryUseCasesFactory {
    fun createGetAlbumsUseCase(): GetAlbumsUseCase

    fun createGetMediaUseCase(): GetMediaUseCase

    fun createGetMediaByTypeUseCase(): GetMediaByTypeUseCase
}