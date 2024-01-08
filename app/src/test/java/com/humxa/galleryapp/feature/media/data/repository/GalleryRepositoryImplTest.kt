package com.humxa.galleryapp.feature.media.data.repository

import android.content.ContentResolver
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.humxa.galleryapp.feature.media.data.model.getAlbums
import com.humxa.galleryapp.feature.media.domain.model.Album
import io.mockk.coEvery
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GalleryRepositoryImplTest {

    private lateinit var context: Context
    private lateinit var contentResolver: ContentResolver
    private lateinit var repository: GalleryRepositoryImpl

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        contentResolver = context.contentResolver
        repository = GalleryRepositoryImpl(context)
    }

    @Test
    fun getAlbums_returnsAlbumsFromContentResolver() = runTest {
        val expectedAlbums = listOf(
            Album(1, "Album 1", pathToThumbnail = "", timestamp = Long.MAX_VALUE),
            Album(1, "Album 1", pathToThumbnail = "", timestamp = Long.MAX_VALUE)
        )
        coEvery { contentResolver.getAlbums() } returns expectedAlbums
        val albumsFlow = repository.getAlbums()
        val actualAlbums = albumsFlow.first()
        Truth.assertThat(actualAlbums).isEqualTo(expectedAlbums)
    }
}