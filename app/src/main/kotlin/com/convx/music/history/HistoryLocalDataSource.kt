package com.hdemirbassmusic.music.history

import com.hdemirbassmusic.music.db.entities.EventWithSong

interface HistoryLocalDataSource {
    suspend fun loadEvents(limit: Int, offset: Int, hideVideoSongs: Boolean, query: String): List<EventWithSong>
}
