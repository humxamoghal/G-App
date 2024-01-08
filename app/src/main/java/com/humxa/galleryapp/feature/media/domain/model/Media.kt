package com.humxa.galleryapp.feature.media.domain.model

import android.net.Uri
import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Media(
    val id: Long = 0,
    val label: String,
    val uri: Uri,
    val path: String,
    val relativePath: String,
    val albumID: Long,
    val albumLabel: String,
    val timestamp: Long,
    val mimeType: String,
    val duration: String? = null,
) : Parcelable {
    val isVideo: Boolean get() = mimeType.startsWith("video/") && duration != null
}