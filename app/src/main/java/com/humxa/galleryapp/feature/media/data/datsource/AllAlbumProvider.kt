package com.humxa.galleryapp.feature.media.data.datsource

import com.humxa.galleryapp.feature.media.domain.model.Album

object AllAlbumProvider {
    fun addAlbums(albums: ArrayList<Album>): ArrayList<Album> = albums.addAllAlbums()
    private fun ArrayList<Album>.addAllAlbums(): ArrayList<Album> {
        if (this.isEmpty()) return this
        val time = System.currentTimeMillis()
        val allVideos = Album(
            id = DEFAULT_VIDEOS_ALBUM_ID,
            label = ALL_VIDEOS,
            pathToThumbnail = this.lastOrNull()?.pathToThumbnail ?: "",
            timestamp = time,
            count = 0,
        )
        val allPhotos = Album(
            id = DEFAULT_PHOTOS_ALBUM_ID,
            label = ALL_PHOTOS,
            pathToThumbnail = this.firstOrNull()?.pathToThumbnail ?: "",
            timestamp = time + 1,
            count = 0,
        )
        this.add(0, allVideos)
        this.add(0, allPhotos)
        return this
    }

    private const val ALL_VIDEOS = "All Videos"
    private const val ALL_PHOTOS = "All Photos"
}

const val DEFAULT_VIDEOS_ALBUM_ID = -200L
const val DEFAULT_PHOTOS_ALBUM_ID = -201L