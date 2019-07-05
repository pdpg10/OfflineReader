package com.example.offlinereader

import android.app.Application
import com.example.offlinereader.storage.pref.IPref
import com.example.offlinereader.storage.pref.PrefImpl

class App : Application() {
    val pref: IPref by lazy { PrefImpl(this) }

    override fun onCreate() {
        super.onCreate()
    }

}