package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.NetworkBrowserViewModel
import com.hdemirbassmusic.music.viewmodels.NetworkStorageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val networkStoragePresentationModule = module {
    viewModelOf(::NetworkStorageViewModel)
    viewModelOf(::NetworkBrowserViewModel)
}
