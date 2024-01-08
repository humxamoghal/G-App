package com.humxa.galleryapp.feature.media.domain.usecase

import com.humxa.galleryapp.feature.media.domain.model.Media
import com.humxa.galleryapp.feature.media.domain.repository.GalleryRepository
import com.humxa.galleryapp.feature.media.presentation.model.MediaType
import kotlinx.coroutines.flow.Flow


class GetMediaByTypeUseCase(
    private val repository: GalleryRepository
) {
    operator fun invoke(mediaType: MediaType): Flow<List<Media>> =
        repository.getMediaByType(mediaType)
}