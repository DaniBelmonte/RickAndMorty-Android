package com.rudo.rickAndMorty

import android.app.Application
import com.rudo.rickAndMorty.data.dataSource.local.FavoriteRoomDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()