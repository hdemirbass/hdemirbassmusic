package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.AlbumViewModel
import com.hdemirbassmusic.music.viewmodels.AiDjSetViewModel
import com.hdemirbassmusic.music.viewmodels.ArtistAlbumsViewModel
import com.hdemirbassmusic.music.viewmodels.ArtistViewModel
import com.hdemirbassmusic.music.viewmodels.ArtistSongsViewModel
import com.hdemirbassmusic.music.viewmodels.SongInsightViewModel
import com.hdemirbassmusic.music.viewmodels.TopPlaylistViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val catalogPresentationModule = module {
    viewModelOf(::AlbumViewModel)
    viewModelOf(::ArtistViewModel)
    viewModelOf(::ArtistSongsViewModel)
    viewModelOf(::ArtistAlbumsViewModel)
    viewModelOf(::TopPlaylistViewModel)
    viewModelOf(::AiDjSetViewModel)
    viewModelOf(::SongInsightViewModel)
}
