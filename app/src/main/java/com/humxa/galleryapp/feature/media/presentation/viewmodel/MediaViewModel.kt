package com.humxa.galleryapp.feature.media.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.humxa.galleryapp.feature.media.domain.usecase.GalleryUseCases
import com.humxa.galleryapp.feature.media.presentation.model.MediaState
import com.humxa.galleryapp.feature.media.presentation.model.MediaType
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
class MediaViewModel @Inject constructor(
    private val useCases: GalleryUseCases
) : ViewModel() {

    private val _mediaState = MutableStateFlow(MediaState())
    val mediaState = _mediaState.asStateFlow()

    private val _screenState = mutableStateOf(ScreenState.LOADING)
    val screenState: State<ScreenState>
        get() = _screenState

    fun getMedia(albumId: Long) = viewModelScope.launch {
        useCases.getMediaUseCase(albumId)
            .flowOn(Dispatchers.IO).collectLatest { result ->
                _screenState.value = ScreenState.DONE
                _mediaState.value = MediaState(media = result)
            }
    }

    fun getMedia(type: MediaType) = viewModelScope.launch {
        useCases.getMediaByTypeUseCase(type)
            .flowOn(Dispatchers.IO).collectLatest { result ->
                _screenState.value = ScreenState.DONE
                _mediaState.value = MediaState(media = result)
            }
    }


}