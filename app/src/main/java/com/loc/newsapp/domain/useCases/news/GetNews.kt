package com.loc.newsapp.domain.useCases.news

import androidx.paging.PagingData
import com.loc.newsapp.data.remote.repository.NewsRepository
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

//UseCase
class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
       return newsRepository.getNews(sources = sources)
    }
}