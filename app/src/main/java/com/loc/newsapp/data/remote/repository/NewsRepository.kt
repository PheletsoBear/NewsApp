package com.loc.newsapp.data.remote.repository

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun  getNews(sources: List<String>): Flow<PagingData<Article>>
}