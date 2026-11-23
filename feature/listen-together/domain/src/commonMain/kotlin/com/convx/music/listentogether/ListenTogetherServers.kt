/**
 * hdemirbassmusic Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.hdemirbassmusic.music.listentogether

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ListenTogetherServer(
    val name: String,
    val url: String,
    val location: String,
    val operator: String
)

object ListenTogetherServers {
    private const val ServersJson = """
        [
          {
            "name": "hdemirbassmusic Sync",
            "url": "wss://hdemirbassmusic-sync.cosmictaser-dev.workers.dev",
            "location": "Global (edge)",
            "operator": "hdemirbassmusic"
          },
          {
            "name": "Hugging Face Sync",
            "url": "wss://devilmi-vivi-music-listen-together.hf.space",
            "location": "Global",
            "operator": "Community"
          },
          {
            "name": "hdemirbassmusic Sync Server",
            "url": "wss://vivimusic-listen-together.onrender.com",
            "location": "USA",
            "operator": "Community"
          }
        ]
    """

    private val json = Json { ignoreUnknownKeys = true }

    val servers: List<ListenTogetherServer> by lazy {
        json.decodeFromString(ServersJson)
    }

    val defaultServerUrl: String
        get() = servers.first().url

    fun findByUrl(url: String): ListenTogetherServer? = servers.firstOrNull { it.url == url }
}
