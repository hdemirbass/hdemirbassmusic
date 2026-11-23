package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.ui.screens.library.LocalMusicViewModel
import com.hdemirbassmusic.music.ui.screens.library.apple.AppleLibraryViewModel
import com.hdemirbassmusic.music.ui.screens.library.apple.AppleLocalSongsViewModel
import com.hdemirbassmusic.music.viewmodels.LocalHomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val localLibraryPresentationModule = module {
    viewModelOf(::LocalMusicViewModel)
    viewModelOf(::LocalHomeViewModel)
    viewModelOf(::AppleLibraryViewModel)
    viewModelOf(::AppleLocalSongsViewModel)
}
