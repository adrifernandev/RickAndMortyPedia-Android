package com.adrifernandev.rickandmortypedia.common.presentation.ui.images

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger

private const val IMAGE_CACHE_DIR = "image_cache"
private const val IMAGE_CACHE_SIZE = 1024 * 1024 * 5
private const val IMAGE_CACHE_MAX_SIZE_PERCENT = 0.25

fun provideImageLoader(context: Context) = ImageLoader.Builder(context)
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
    .crossfade(true)
    .logger(DebugLogger())
    .build()