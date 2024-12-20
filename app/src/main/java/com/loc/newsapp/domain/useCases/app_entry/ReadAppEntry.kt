package com.loc.newsapp.domain.useCases.app_entry

import com.loc.newsapp.data.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
){
   operator fun invoke(): Flow<Boolean>
    {
       return localUserManager.readAppEntry()
    }
}