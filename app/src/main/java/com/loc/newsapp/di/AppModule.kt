package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manager.LocalManagerImpl
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.remote.repository.NewsRepository
import com.loc.newsapp.data.remote.repository.NewsRepositoryImp
import com.loc.newsapp.data.manager.LocalUserManager
import com.loc.newsapp.domain.useCases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.useCases.app_entry.ReadAppEntry
import com.loc.newsapp.domain.useCases.app_entry.SaveAppEntry
import com.loc.newsapp.domain.useCases.news.GetNews
import com.loc.newsapp.domain.useCases.news.NewsUseCases
import com.loc.newsapp.domain.useCases.news.SearchNews
import com.loc.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)


    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImp(newsApi)

    @Provides
    @Singleton
    fun providesNewsUseCases(newsRepository: NewsRepository): NewsUseCases =
        NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    
}