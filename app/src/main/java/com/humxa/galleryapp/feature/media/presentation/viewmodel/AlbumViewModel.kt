package com.humxa.galleryapp.feature.media.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.humxa.galleryapp.feature.media.domain.usecase.GalleryUseCases
import com.humxa.galleryapp.feature.media.presentation.model.AlbumState
import com.humxa.galleryapp.feature.media.presentation.model.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val useCases: GalleryUseCases
) : ViewModel() {

    private val _albumsState = MutableStateFlow(AlbumState())
    val albumsState = _albumsState.asStateFlow()

    private val _screenState = mutableStateOf(ScreenState.LOADING)
    val screenState: State<ScreenState>
        get() = _screenState

    init {
        getAlbums()
    }

    fun getAlbums() = viewModelScope.launch {
        _screenState.value = ScreenState.LOADING
        useCases.getAlbumsUseCase()
            .flowOn(Dispatchers.IO).collectLatest { result ->
                _screenState.value = ScreenState.DONE
                _albumsState.value = AlbumState(albums = result)
            }
    }
}