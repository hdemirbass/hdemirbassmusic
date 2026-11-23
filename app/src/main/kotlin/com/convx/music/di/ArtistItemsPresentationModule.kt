package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.ArtistItemsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val artistItemsPresentationModule = module {
    viewModelOf(::ArtistItemsViewModel)
}
