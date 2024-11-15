package com.loc.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
   private val newsUseCases: NewsUseCases
): ViewModel() {

   private val sources = listOf("cnn","bbc-news, al-jazeera-english")

   val news = newsUseCases.getNews(sources)
      .cachedIn(viewModelScope)
}