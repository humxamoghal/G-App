package com.humxa.galleryapp.feature.media.presentation.view

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.humxa.galleryapp.feature.media.presentation.navigation.SetupNavGraph
import com.humxa.galleryapp.ui.theme.GalleryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryAppTheme {
                val navController = rememberNavController()
                Box {
                    SetupNavGraph(navController)
                }
            }
        }
    }
}
