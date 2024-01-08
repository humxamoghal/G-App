package com.humxa.galleryapp

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.rule.GrantPermissionRule
import com.humxa.galleryapp.feature.media.domain.model.Album
import com.humxa.galleryapp.feature.media.presentation.components.AlbumScreen
import com.humxa.galleryapp.feature.media.presentation.model.AlbumState
import com.humxa.galleryapp.feature.media.presentation.model.ScreenState
import com.humxa.galleryapp.ui.theme.GalleryAppTheme
import org.junit.Rule
import org.junit.Test


class AlbumScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.READ_MEDIA_VIDEO,
        android.Manifest.permission.READ_MEDIA_IMAGES,
    )



    @Test
    fun galleryScreenIsDisplayed(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            GalleryAppTheme {
                AlbumScreen(
                    navController = navController,
                    screenState = ScreenState.LOADING,
                    albumState = AlbumState(),
                    getAlbums = {}
                )
            }
            
        }
        composeTestRule.onNodeWithText("Gallery").assertIsDisplayed()
    }

    @Test
    fun galleryScreenEmptyState_retryButtonIsShown(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            GalleryAppTheme {
                AlbumScreen(
                    navController = navController,
                    screenState = ScreenState.DONE,
                    albumState = AlbumState(),
                    getAlbums = {}
                )
            }

        }
        Thread.sleep(3000)
        composeTestRule.onNodeWithText("Retry", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun galleryScreen_IfThereIsAlbum_ItShouldBeVisible(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            GalleryAppTheme {
                AlbumScreen(
                    navController = navController,
                    screenState = ScreenState.DONE,
                    albumState = AlbumState(albums = listOf(Album(0, "ABC_Album", "0",0,1, false))),
                    getAlbums = {}
                )
            }

        }
        Thread.sleep(10000)
        composeTestRule.onNodeWithContentDescription("ABC_Album").assertIsDisplayed()
    }

}