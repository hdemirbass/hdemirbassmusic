package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.viewmodels.LibraryAlbumsViewModel
import com.hdemirbassmusic.music.viewmodels.LibraryArtistsViewModel
import com.hdemirbassmusic.music.viewmodels.LibraryMixViewModel
import com.hdemirbassmusic.music.viewmodels.LibraryPlaylistsViewModel
import com.hdemirbassmusic.music.viewmodels.LibrarySongsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val librarySectionsPresentationModule = module {
    viewModelOf(::LibrarySongsViewModel)
    viewModelOf(::LibraryArtistsViewModel)
    viewModelOf(::LibraryAlbumsViewModel)
    viewModelOf(::LibraryPlaylistsViewModel)
    viewModelOf(::LibraryMixViewModel)
}
