package com.adrifernandev.rickandmortypedia.common.presentation.di

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private const val IMAGE_CACHE_SIZE = 5 * 1024 * 1024
private const val IMAGE_CACHE_DIR = "image_cache"
private const val IMAGE_CACHE_MAX_SIZE_PERCENT = 0.25

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideImageLoader(
        @ApplicationContext context: Context
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, IMAGE_CACHE_MAX_SIZE_PERCENT)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve(IMAGE_CACHE_DIR))
                    .maxSizeBytes(IMAGE_CACHE_SIZE.toLong())
                    .build()
            }
            .build()
    }
}