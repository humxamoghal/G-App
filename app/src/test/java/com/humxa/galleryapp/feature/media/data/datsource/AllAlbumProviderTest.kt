package com.humxa.galleryapp.feature.media.data.datsource

import com.google.common.truth.Truth
import com.humxa.galleryapp.feature.media.domain.model.Album
import org.junit.Test

class AllAlbumProviderTest {

    @Test
    fun `addAlbums adds all albums correctly`() {
        // Given
        val albums = arrayListOf(
            Album(1, "Album 1", "/path/to/thumbnail1", 12345L, 10),
            Album(2, "Album 2", "/path/to/thumbnail2", 67890L, 15)
        )
        val result = AllAlbumProvider.addAlbums(albums)
        Truth.assertThat(result.size).isEqualTo(4)
        Truth.assertThat(result.first().id).isEqualTo(DEFAULT_PHOTOS_ALBUM_ID)
        Truth.assertThat(result[1].id).isEqualTo(DEFAULT_VIDEOS_ALBUM_ID)
    }
}
