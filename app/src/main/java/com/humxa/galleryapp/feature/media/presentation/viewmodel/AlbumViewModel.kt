package com.humxa.galleryapp.feature.media.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.humxa.galleryapp.feature.media.domain.usecase.GalleryUseCases
import com.humxa.galleryapp.feature.media.presentation.model.AlbumState
import com.humxa.galleryapp.feature.media.presentation.model.MediaState
import com.humxa.galleryapp.feature.media.presentation.model.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val useCases: GalleryUseCases
) : ViewModel() {

    private val _albumName: MutableStateFlow<String> = MutableStateFlow("")
    val albumName: StateFlow<String>
        get() = _albumName.asStateFlow()

    private val _albumsState = MutableStateFlow(AlbumState())
    val albumsState = _albumsState.asStateFlow()


    private val _screenState = MutableStateFlow(ScreenState.LOADING)
    val screenState = _screenState.asStateFlow()


    fun getAlbum() {

    }



}