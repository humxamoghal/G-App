package com.humxa.galleryapp.feature.media.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.humxa.galleryapp.feature.media.domain.usecase.GalleryUseCases
import com.humxa.galleryapp.feature.media.presentation.model.MediaState
import com.humxa.galleryapp.feature.media.presentation.model.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val useCases: GalleryUseCases
) : ViewModel() {

    private val _mediaState = MutableStateFlow(MediaState())
    val mediaState = _mediaState.asStateFlow()

    private val _screenState = MutableStateFlow(ScreenState.LOADING)
    val screenState = _screenState.asStateFlow()

    private fun getMedia(albumId: Long) {

    }
}