package com.humxa.galleryapp.feature.media.domain.factory

import com.google.common.truth.Truth
import com.humxa.galleryapp.feature.media.domain.repository.GalleryRepository
import com.humxa.galleryapp.feature.media.domain.usecase.GetAlbumsUseCase
import com.humxa.galleryapp.feature.media.domain.usecase.GetMediaByTypeUseCase
import com.humxa.galleryapp.feature.media.domain.usecase.GetMediaUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GalleryUseCasesFactoryImplTest {

    @MockK
    private lateinit var repository: GalleryRepository

    private lateinit var factory: GalleryUseCasesFactoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        factory = GalleryUseCasesFactoryImpl(repository)
    }

    @Test
    fun `createGetAlbumsUseCase returns GetAlbumsUseCase`() = runTest {
        val useCase = factory.createGetAlbumsUseCase()
        Truth.assertThat(useCase).isInstanceOf(GetAlbumsUseCase::class.java)
    }

    @Test
    fun `createGetMediaUseCase returns GetMediaUseCase`() = runTest {
        val useCase = factory.createGetMediaUseCase()
        Truth.assertThat(useCase).isInstanceOf(GetMediaUseCase::class.java)
    }

    @Test
    fun `createGetMediaByTypeUseCase returns GetMediaByTypeUseCase`() = runTest {
        val useCase = factory.createGetMediaByTypeUseCase()
        Truth.assertThat(useCase).isInstanceOf(GetMediaByTypeUseCase::class.java)
    }
}