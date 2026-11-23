package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.YouTubeBrowseViewModel
import com.hdemirbassmusic.music.viewmodels.BrowseViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val browsePresentationModule = module {
    viewModelOf(::YouTubeBrowseViewModel)
    viewModelOf(::BrowseViewModel)
}
