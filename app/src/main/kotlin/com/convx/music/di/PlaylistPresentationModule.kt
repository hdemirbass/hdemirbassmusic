package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.AppleMusicImportViewModel
import com.hdemirbassmusic.music.viewmodels.AutoPlaylistViewModel
import com.hdemirbassmusic.music.viewmodels.PlaylistsViewModel
import com.hdemirbassmusic.music.viewmodels.SpotifyImportViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playlistPresentationModule = module {
    viewModelOf(::PlaylistsViewModel)
    viewModelOf(::AutoPlaylistViewModel)
    viewModelOf(::SpotifyImportViewModel)
    viewModelOf(::AppleMusicImportViewModel)
}
