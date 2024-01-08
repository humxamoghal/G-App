package com.humxa.galleryapp.core

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun Context.contentFlowObserver(uris: Array<Uri>) = callbackFlow {
    val mutex = Mutex()
    val observer = object : ContentObserver(null) {
        override fun onChange(selfChange: Boolean, uri: Uri?) {
            if (isActive) {
                launch(Dispatchers.IO) {
                    mutex.withLock {
                        trySend(selfChange)
                    }
                }
            }
        }
    }
    for (uri in uris) contentResolver.registerContentObserver(uri, true, observer)
    launch(Dispatchers.IO) {
        mutex.withLock {
            trySend(false)
        }
    }
    awaitClose {
        contentResolver.unregisterContentObserver(observer)
    }
}