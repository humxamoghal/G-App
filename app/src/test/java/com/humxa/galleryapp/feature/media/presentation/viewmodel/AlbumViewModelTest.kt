package com.humxa.galleryapp.feature.media.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.humxa.galleryapp.feature.media.domain.model.Album
import com.humxa.galleryapp.feature.media.domain.usecase.GalleryUseCases
import com.humxa.galleryapp.feature.media.presentation.model.ScreenState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AlbumViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: AlbumViewModel

    @MockK
    private lateinit var useCases: GalleryUseCases

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = AlbumViewModel(useCases)
    }


    @Test
    fun `getAlbums updates albumsState and screenState correctly`() = runTest {
        val albums = listOf(Album(1, "Album 1", "/path/to/thumbnail1", 12345L, 10))
        val flow = flowOf(albums)
        coEvery { useCases.getAlbumsUseCase().flowOn(Dispatchers.IO) } returns flow
        viewModel.getAlbums()
        viewModel.albumsState.test {
            // Initial state
            assert(awaitItem().albums.isEmpty())
            // Loading state
            viewModel.screenState.value.let { loadingState ->
                assert(loadingState == ScreenState.LOADING)
            }
            // Done state
            viewModel.screenState.value.let { doneState ->
                assert(doneState == ScreenState.DONE)
            }
            // Updated albums state
            assert(awaitItem().albums == albums)
            // Verify no more items emitted
            expectNoEvents()
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}