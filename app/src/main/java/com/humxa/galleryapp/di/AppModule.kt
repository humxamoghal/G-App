package com.humxa.galleryapp.di

import android.content.ContentResolver
import android.content.Context
import com.humxa.galleryapp.feature.media.data.repository.GalleryRepositoryImpl
import com.humxa.galleryapp.feature.media.domain.repository.GalleryRepository
import com.humxa.galleryapp.feature.media.domain.usecase.GalleryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
        return context.contentResolver
    }

    @Provides
    @Singleton
    fun provideMediaUseCases(
        repository: GalleryRepository,
    ): GalleryUseCases {
        return GalleryUseCases(repository)
    }

    @Provides
    @Singleton
    fun provideMediaRepository(
        @ApplicationContext context: Context
    ): GalleryRepository {
        return GalleryRepositoryImpl(context)
    }

}