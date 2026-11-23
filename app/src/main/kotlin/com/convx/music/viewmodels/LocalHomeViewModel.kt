/**
 * hdemirbassmusic Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.hdemirbassmusic.music.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hdemirbassmusic.music.constants.LocalAlbumsByYearKey
import com.hdemirbassmusic.music.constants.LocalSongSortDescendingKey
import com.hdemirbassmusic.music.constants.LocalSongSortTypeKey
import com.hdemirbassmusic.music.constants.SongSortType
import com.hdemirbassmusic.music.db.MusicDatabase
import com.hdemirbassmusic.music.db.entities.Album
import com.hdemirbassmusic.music.db.entities.Artist
import com.hdemirbassmusic.music.db.entities.Song
import com.hdemirbassmusic.music.extensions.toEnum
import com.hdemirbassmusic.music.library.ObserveLibraryPlaylists
import com.hdemirbassmusic.music.library.PlaylistOrder
import com.hdemirbassmusic.music.library.PlaylistSummary
import com.hdemirbassmusic.music.utils.LocalAudioScanner
import com.hdemirbassmusic.music.utils.LocalFolderIndex
import com.hdemirbassmusic.music.utils.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/** Home in local-only mode: Room rows and the folder index, never the network. */
class LocalHomeViewModel(
    private val context: Context,
    private val database: MusicDatabase,
    observeLibraryPlaylists: ObserveLibraryPlaylists,
) : ViewModel() {
    val songs: StateFlow<List<Song>> = context.dataStore.data
        .map { it[LocalSongSortTypeKey].toEnum(SongSortType.NAME) to (it[LocalSongSortDescendingKey] ?: false) }
        .distinctUntilChanged()
        .flatMapLatest { (sortType, descending) -> database.localSongs(sortType, descending) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Newest release first by default; SQLite sorts files without a year last on DESC.
    val albums: StateFlow<List<Album>> = context.dataStore.data
        .map { it[LocalAlbumsByYearKey] ?: true }
        .distinctUntilChanged()
        .flatMapLatest { byYear -> if (byYear) database.albumsLocalByYearDesc() else database.albumsLocalByNameAsc() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val artists: StateFlow<List<Artist>> = database.artistsLocalByNameAsc()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val playlists: StateFlow<List<PlaylistSummary>> = observeLibraryPlaylists(
        order = PlaylistOrder.CREATED,
        descending = true,
        hideYoutubeShorts = false,
        localOnly = true,
    )
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val folders = MutableStateFlow<List<LocalFolderIndex.Folder>>(emptyList())
    val isRefreshing = MutableStateFlow(false)

    init {
        viewModelScope.launch(Dispatchers.IO) { loadFolders() }
    }

    /** Rescans the device, then rebuilds the folder index. */
    fun refresh() {
        if (isRefreshing.value) return
        viewModelScope.launch(Dispatchers.IO) {
            isRefreshing.value = true
            try {
                runCatching { LocalAudioScanner.scanAndInsert(context, database) }
                loadFolders()
            } finally {
                isRefreshing.value = false
            }
        }
    }

    private suspend fun loadFolders() {
        folders.value = runCatching { LocalFolderIndex.load(context) }.getOrDefault(emptyList())
    }
}
