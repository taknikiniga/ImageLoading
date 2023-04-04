package com.taknikiniga.imageloading.di

import android.app.Application
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

//        GlideBuilder().setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
//            .setLogLevel(Log.ERROR).build(this)
    }
}