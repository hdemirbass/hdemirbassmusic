package com.hdemirbassmusic.music.di

import com.hdemirbassmusic.music.ui.screens.search.AppleSearchViewModel
import com.hdemirbassmusic.music.ui.screens.search.suggestions.SuggestionsViewModel
import com.hdemirbassmusic.music.viewmodels.MoodAndGenresViewModel
import com.hdemirbassmusic.music.viewmodels.OnlineSearchViewModel
import com.hdemirbassmusic.music.viewmodels.OnlineSearchSuggestionViewModel
import com.hdemirbassmusic.music.viewmodels.ExploreViewModel
import com.hdemirbassmusic.music.viewmodels.LocalSearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchPresentationModule = module {
    viewModelOf(::AppleSearchViewModel)
    viewModelOf(::SuggestionsViewModel)
    viewModelOf(::MoodAndGenresViewModel)
    viewModelOf(::OnlineSearchViewModel)
    viewModelOf(::OnlineSearchSuggestionViewModel)
    viewModelOf(::ExploreViewModel)
    viewModelOf(::LocalSearchViewModel)
}
