package com.dev.mahmoud_ashraf.popular_actors_app.presentation.core

import android.content.Context
import android.os.Environment

fun getExternalDir(context: Context): String {
    val state = Environment.getExternalStorageState()
    // to check if Sd card available
    return if (Environment.MEDIA_MOUNTED == state) {
        context.getExternalFilesDir(null)!!.absolutePath
    } else {
        context.filesDir.absolutePath
    }
}