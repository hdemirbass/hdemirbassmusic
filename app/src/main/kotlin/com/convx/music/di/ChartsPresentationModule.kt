package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.ChartsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chartsPresentationModule = module {
    viewModelOf(::ChartsViewModel)
}
