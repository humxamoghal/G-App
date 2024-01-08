package com.humxa.galleryapp.feature.media.domain.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Album(
    val id: Long = 0,
    val label: String,
    val pathToThumbnail: String,
    val timestamp: Long,
    var count: Long = 0,
    val selected: Boolean = false,
) : Parcelable