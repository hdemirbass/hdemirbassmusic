package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.UpdateViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val updaterPresentationModule = module {
    viewModelOf(::UpdateViewModel)
}
