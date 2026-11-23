package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.LyricsAppearanceViewModel
import com.hdemirbassmusic.music.viewmodels.LyricsMenuViewModel
import com.hdemirbassmusic.music.viewmodels.LyricsProviderViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val lyricsPresentationModule = module {
    viewModelOf(::LyricsAppearanceViewModel)
    viewModelOf(::LyricsMenuViewModel)
    viewModelOf(::LyricsProviderViewModel)
}
